package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.domain.PostWriteRequest;
import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.PostNotFoundException;
import com.academy.board.bootboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String getPostsList(HttpServletRequest request, Model model) {
        List<Post> posts = postService.getVisiblePosts(true);
        User user = (User) request.getSession().getAttribute("LoginUser");

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "postList";
    }

    @GetMapping("/post")
    public String getPost(@RequestParam("id") int postId, Model model) throws PostNotFoundException {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/post/write")
    public String getWriteForm() {
        return "writeForm";
    }

    @PostMapping("/post/write")
    public String doWritePost(@Validated @ModelAttribute PostWriteRequest postWriteRequest,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        int userId = getLoginUserId(request);

        postService.writePost(postWriteRequest.getTitle(), postWriteRequest.getContent(), userId);

        return "redirect:/posts";

    }

    private int getLoginUserId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("LoginUser");
        if (user == null) {
            throw new RuntimeException();
        }

        return user.getUserId();
    }
}
