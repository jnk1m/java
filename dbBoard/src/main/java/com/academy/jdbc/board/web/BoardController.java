package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.service.BoardService;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;
    private final BoardService boardService;

    public BoardController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @GetMapping()
    public String getPostList(Model model){
        List<Board> boards = boardService.getAllBoard();
        for (Board board : boards) {
            System.out.println("!!"+board.getModifier());
        }
        model.addAttribute("boards",boards);
        return "board";
    }

    @GetMapping("/post")
    public String getPostDetail(@RequestParam("id") int id){
        //postService.
        return "postDetail";
    }
}
