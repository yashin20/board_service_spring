package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.board_service.dto.MemberDto;
import project.board_service.exception.DataAlreadyExistsException;
import project.board_service.exception.PasswordCheckFailedException;
import project.board_service.service.MemberService;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인(Login) - "/members/login"
     */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new MemberDto.Request());

        return "members/login";
    }

    /**
     * 회원가입(Join) - "/members/join"
     */
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinForm", new MemberDto.Request());

        return "members/join";
    }
    @PostMapping("/join")
    public String join(@ModelAttribute("joinForm") @Validated(MemberDto.Request.Create.class) MemberDto.Request memberDto,
                       BindingResult bindingResult, Model model) {

        /*'유효성 검사' 에러처리*/
        if(bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);

            return "members/join";
        }

        try {
            memberService.createMember(memberDto);
        } catch (DataAlreadyExistsException | PasswordCheckFailedException ex) {
            bindingResult.reject("errorMessage", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());

            return "members/join";
        }

        return "redirect:/";
    }
}
