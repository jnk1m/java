package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.*;
import com.academy.jdbc.board.exception.NoPermissionException;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/community")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String getPostList(Model model, HttpServletRequest request) {
        List<Board> allPosts = postService.getAllPosts();
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");


        model.addAttribute("posts", allPosts);
        model.addAttribute("user", user);
        return "postList";
    }

    @GetMapping("/post")
    public String getPostDetailWithComments(@RequestParam("id") int postId, Model model) {

        Optional<Post> post = postService.getPost(postId);
        List<Optional<Comment>> comments = postService.getAllComments(postId);

        for (Optional<Comment> comment : comments) {
            System.out.println(comment.get().getContent() + "!!!!");
        }

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "postDetail";
    }

    @GetMapping("/write")
    public String getWriteForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String doWritePost(@Valid @ModelAttribute WriteRequest writeRequest,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int userId = user.get().getId();

        postService.writePost(writeRequest.getTitle(), writeRequest.getContent(), userId);

        return "redirect:/community/list";
    }

    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") int postId,
                                HttpServletRequest request,
                                Model model) throws NoPermissionException {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");

        int userId = user.get().getId();

        Optional<Post> post = postService.getPost(postId);

        if (post.get().getCreated_by().getId() != userId) {
            throw new NoPermissionException();
        }
        model.addAttribute("post", post);
        return "updateForm";
    }

    @PostMapping("/update")
    public String doUpdatePost(@Valid @ModelAttribute WriteRequest writeRequest,
                               BindingResult bindingResult,
                               @RequestParam("id") int postId,
                               HttpServletRequest request) throws NoPermissionException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int updateUserId = user.get().getId();

        postService.updatePost(writeRequest.getTitle(), writeRequest.getContent(), updateUserId, postId);

        return "redirect:/community/post?id=" + postId;
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam("id") int postId,
                             HttpServletRequest request) throws NoPermissionException {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int userId = user.get().getId();

        postService.setPostInvisible(userId, postId);
        return "redirect:/community/list";
    }

    @PostMapping("/writeComment")
    public String writeComment(@Valid @ModelAttribute CommentRequest commentRequest,
                               BindingResult bindingResult,
                               @RequestParam("postId") int postId,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int userId = user.get().getId();

        postService.writeComment(commentRequest.getContent(), postId, userId);

        return "redirect:/community/post?id=" + postId;
    }



}
