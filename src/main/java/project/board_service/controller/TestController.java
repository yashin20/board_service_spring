package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/index")
    public String testIndex() {
        return "test/index";
    }
}
