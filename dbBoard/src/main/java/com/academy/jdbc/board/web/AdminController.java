package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.exception.NoPermissionException;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    PostService postService;

    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/deletedPosts")
    public String getAdminPostList(HttpServletRequest request,
                                   Model model) throws NoPermissionException {

        List<Board> deletedPosts = postService.getAllDeletedPosts();
        model.addAttribute("deletedPosts", deletedPosts);

        return "deletedPostList";
    }

    @GetMapping("/restorePost")
    public String restorePost(@RequestParam("id") int postId) {
        postService.setPostVisible(postId);
        return "redirect:/admin/deletedPosts";
    }
}
