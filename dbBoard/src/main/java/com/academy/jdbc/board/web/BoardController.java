package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    PostService postService;

    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String getPostList(Model model){
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts",posts);
        return "board";
    }
}
