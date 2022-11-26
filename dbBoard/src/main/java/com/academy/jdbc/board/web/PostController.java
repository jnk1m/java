package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/community")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String getPostList(Model model) {
        List<Board> allPosts = postService.getAllPosts();
        model.addAttribute("posts", allPosts);
        return "postList";
    }

    @GetMapping("/post")
    public String getPostDetail(@RequestParam("id") int postId, Model model) {

        Optional<Post> post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/write")
    public String getWriteForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String doWritePost(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              HttpServletRequest request,
                              Model model) {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int name = user.get().getId();

        postService.writePost(title, content, name);

        return "redirect:/community";
    }

    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") int postId, Model model){
        Optional<Post> post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "updateForm";
    }

    @PostMapping("/update")
    public String doUpdatePost(@RequestParam("title") String title,
                               @RequestParam("content") String content,
                               @RequestParam("id") int postId,
                               HttpServletRequest request,
                               Model model){
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        int updateUserId = user.get().getId();

        postService.updatePost(title, content,updateUserId, postId);

        return "redirect:/community/post?id="+postId;
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam("id") int postId){
        postService.setPostInvisible(postId);
        return "redirect:/community";
    }
}
