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

    /*게시글 목록 불러오기*/
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

    /*게시글 및 댓글 상세 보기*/
    @GetMapping("/post")
    public String getPostDetailWithComments(@RequestParam("id") int postId, Model model) {

        Optional<Post> post = postService.getPost(postId);
        List<Optional<Comment>> comments = postService.getAllComments(postId);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "postDetail";
    }

    /*게시글 작성 폼 불러오기*/
    @GetMapping("/write")
    public String getWriteForm() {
        return "writeForm";
    }

    /*게시글 작성하기*/
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

    /*게시글 수정 폼 불러오기*/
    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") int postId,
                                HttpServletRequest request,
                                Model model) throws NoPermissionException {
        int userId = getLoginUserId(request);

        Optional<Post> post = postService.getPost(postId);

        if (post.get().getCreated_by().getId() != userId && userId != 1) {
            throw new NoPermissionException();
        }

        model.addAttribute("post", post);
        return "updateForm";
    }

    /*게시글 수정하기*/
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

    /*게시글 삭제하기*/
    @GetMapping("/delete")
    public String deletePost(@RequestParam("id") int postId,
                             HttpServletRequest request) throws NoPermissionException {
        int userId = getLoginUserId(request);

        postService.setPostInvisible(userId, postId);
        return "redirect:/community/list";
    }

    /*댓글 작성하기*/
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

    /*댓글 수정 폼 불러오기*/
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

    /*댓글 수정하기*/
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

    /*댓글 삭제하기*/
    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") int commentId,
                                HttpServletRequest request) throws NoPermissionException {
        int userId = getLoginUserId(request);

        postService.setCommentInvisible(userId, commentId);
        return "redirect:/community/post?id=" + postService.getComment(commentId).get().getPost_id();
    }

    /*세션에서 로그인한 회원 아이디 가져오기*/
    private int getLoginUserId(HttpServletRequest request) {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        return user.get().getId();
    }


}
