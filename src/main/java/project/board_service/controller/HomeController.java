package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.board_service.service.PostService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    /** Home Page - ("/") **/
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("postList", postService.findAllPosts());
        return "index";
    }
}
