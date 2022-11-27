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
import java.util.Comparator;
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

        allPosts.sort(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return o1.getId() - o2.getId();
            }
        });

        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");

        model.addAttribute("posts", allPosts);
        model.addAttribute("user", user);
        return "postList";
    }

    @GetMapping("/post")
    public String getPostDetailWithComments(@RequestParam("id") int postId, Model model) {

        Optional<Post> post = postService.getPost(postId);
        List<Optional<Comment>> comments = postService.getAllComments(postId);

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

        int userId = getLoginUserId(request);

        postService.writePost(writeRequest.getTitle(), writeRequest.getContent(), userId);

        return "redirect:/community/list";
    }

    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") int postId,
                                HttpServletRequest request,
                                Model model) throws NoPermissionException {
        int userId = getLoginUserId(request);

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

        int userId = getLoginUserId(request);

        postService.updatePost(writeRequest.getTitle(), writeRequest.getContent(), userId, postId);

        return "redirect:/community/post?id=" + postId;
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam("id") int postId,
                             HttpServletRequest request) throws NoPermissionException {
        int userId = getLoginUserId(request);

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

        int userId = getLoginUserId(request);

        postService.writeComment(commentRequest.getContent(), postId, userId);

        return "redirect:/community/post?id=" + postId;
    }

    @GetMapping("/updateComment")
    public String getUpdateCommentForm(@RequestParam("id") int id,
                                       HttpServletRequest request,
                                       Model model) throws NoPermissionException {
        Optional<Comment> comment = postService.getComment(id);

        int userId = getLoginUserId(request);

        if (comment.get().getWriter().getId() != userId) {
            throw new NoPermissionException();
        }

        model.addAttribute("comment", comment);
        return "updateCommentForm";
    }

    @PostMapping("/updateComment")
    public String doUpdateComment(@Valid @ModelAttribute CommentRequest commentRequest,
                                  BindingResult bindingResult,
                                  @RequestParam("commentId") int commentId,
                                  @RequestParam("writerId") int writerId,
                                  @RequestParam("postId") int postId,
                                  HttpServletRequest request) throws NoPermissionException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        int userId = getLoginUserId(request);

        postService.updateComment(commentRequest.getContent(), commentId, writerId, userId);

        return "redirect:/community/post?id=" + postId;


    }
    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") int commentId,
                             HttpServletRequest request) throws NoPermissionException {
        int userId = getLoginUserId(request);

        postService.setCommentInvisible(userId, commentId);
        return "redirect:/community/post?id=" + postService.getComment(commentId).get().getPost_id();
    }

    private int getLoginUserId(HttpServletRequest request) {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        return user.get().getId();
    }


}
