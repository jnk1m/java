package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.domain.PostWriteRequest;
import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.PostNotFoundException;
import com.academy.board.bootboard.service.PostService;
import com.academy.board.bootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String getPostsList(Model model) {
        List<Post> posts = postService.getVisiblePosts(true);
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable("id") int postId, Model model) throws PostNotFoundException {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/write")
    public String getWriteForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String doWritePost(@Valid @ModelAttribute PostWriteRequest postWriteRequest,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = "Validation error." +
                    " field: " + fieldError.getField() +
                    ", code: " + Arrays.toString(fieldError.getCodes()) +
                    ", message : " + fieldError.getDefaultMessage();

            log.error(errorMessage);
            throw new ValidationException(errorMessage);
        }

        int userId = getLoginUserId(request);

        postService.writePost(postWriteRequest.getTitle(), postWriteRequest.getContent(), userId);

        return "redirect:/posts";

    }

    private int getLoginUserId(HttpServletRequest request) {
        String userName = (String)request.getSession().getAttribute("userId");
        User user = userService.getUser(userName);

        return user.getUserId();
    }

}
