package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.board_service.dto.MemberDto;
import project.board_service.entity.Member;
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
        if (bindingResult.hasErrors()) {
            //username
            FieldError usernameError = bindingResult.getFieldError("username");
            if (usernameError != null) {
                model.addAttribute("usernameError", usernameError.getDefaultMessage());
            }

            //nickname
            FieldError nicknameError = bindingResult.getFieldError("nickname");
            if (nicknameError != null) {
                model.addAttribute("nicknameError", nicknameError.getDefaultMessage());
            }

            //email
            FieldError emailError = bindingResult.getFieldError("email");
            if (emailError != null) {
                model.addAttribute("emailError", emailError.getDefaultMessage());
            }

            //phone
            FieldError phoneError = bindingResult.getFieldError("phone");
            if (phoneError != null) {
                model.addAttribute("phoneError", phoneError.getDefaultMessage());
            }

            //newPassword
            FieldError newPasswordError = bindingResult.getFieldError("newPassword");
            if (newPasswordError != null) {
                model.addAttribute("newPasswordError", newPasswordError.getDefaultMessage());
            }

            //newPassword2
            FieldError newPassword2Error = bindingResult.getFieldError("newPassword2");
            if (newPassword2Error != null) {
                model.addAttribute("newPassword2Error", newPassword2Error.getDefaultMessage());
            }

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

    /**
     * 회원 정보 (member information) - "/members/info"
     */
    @GetMapping("/info")
    public String memberInfo(Model model) {
        //현재 로그인된 회원
        Member currentMember = memberService.getCurrentMember();

        //ResponseDto 로 반환
        model.addAttribute("currentMember", new MemberDto.Response(currentMember));

        return "members/info";
    }

    /**
     * 회원 정보 수정 (member information update) - "/members/info/update"
     * 1. nickname , email , phone 수정
     */
    @GetMapping("/info/update")
    public String memberInfoUpdateForm(Model model) {
        //현재 로그인된 회원
        Member currentMember = memberService.getCurrentMember();

        //RequestDto 에 데이터 채우기
        MemberDto.Request dto = new MemberDto.Request();
        dto.setId(currentMember.getId());
        dto.setUsername(currentMember.getUsername());
        dto.setNickname(currentMember.getNickname());
        dto.setEmail(currentMember.getEmail());
        dto.setPhone(currentMember.getPhone());
        model.addAttribute("updateForm", dto);

        return "members/infoUpdate";
    }
    @PostMapping("/info/update")
    public String memberInfoUpdate(@ModelAttribute("updateForm") @Validated(MemberDto.Request.Update.class) MemberDto.Request dto,
                                   BindingResult bindingResult, Model model) {

        /*'유효성 검사' 에러처리*/
        if (bindingResult.hasErrors()) {
            //nickname
            FieldError nicknameError = bindingResult.getFieldError("nickname");
            if (nicknameError != null) {
                model.addAttribute("nicknameError", nicknameError.getDefaultMessage());
            }

            //email
            FieldError emailError = bindingResult.getFieldError("email");
            if (emailError != null) {
                model.addAttribute("emailError", emailError.getDefaultMessage());
            }

            //phone
            FieldError phoneError = bindingResult.getFieldError("phone");
            if (phoneError != null) {
                model.addAttribute("phoneError", phoneError.getDefaultMessage());
            }

            return "members/infoUpdate";
        }

        try {
            //회원 정보 업데이트
            memberService.updateMember(dto);
        }
        // 중복 검사 오류 시, 에러 처리 로직
        catch (DataAlreadyExistsException | PasswordCheckFailedException ex) {
            bindingResult.reject("errorMessage", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());

            return "members/infoUpdate";
        }

        return "redirect:/members/info";
    }

    /**
     * 회원 정보 수정 (member information update) - "/members/info/updatePassword"
     * 2. password 수정
     */
    @GetMapping("/info/updatePassword")
    public String updatePasswordForm(Model model) {
        //현재 로그인된 회원
        Member currentMember = memberService.getCurrentMember();
        MemberDto.Request dto = new MemberDto.Request();
        dto.setId(currentMember.getId());
        dto.setUsername(currentMember.getUsername());
        model.addAttribute("updatePasswordForm", dto);

        return "members/passwordUpdate";
    }
    @PostMapping("/info/updatePassword")
    public String updatePassword(@ModelAttribute("updatePasswordForm") @Validated(MemberDto.Request.UpdatePassword.class) MemberDto.Request dto,
                                 BindingResult bindingResult, Model model) {
        /*'유효성 검사' 에러처리*/
        if (bindingResult.hasErrors()) {
            //password - 현재 비밀번호
            FieldError passwordError = bindingResult.getFieldError("password");
            if (passwordError != null) {
                model.addAttribute("passwordError", passwordError.getDefaultMessage());
            }

            //newPassword - 새로운 비밀번호
            FieldError newPasswordError = bindingResult.getFieldError("newPassword");
            if (newPasswordError != null) {
                model.addAttribute("newPasswordError", newPasswordError.getDefaultMessage());
            }

            //newPassword2 - 새로운 비밀번호 이중확인
            FieldError newPassword2Error = bindingResult.getFieldError("newPassword2");
            if (newPassword2Error != null) {
                model.addAttribute("newPassword2Error", newPassword2Error.getDefaultMessage());
            }

            return "members/passwordUpdate";
        }

        try {
            //회원 정보 업데이트
            memberService.updateMember(dto);
        }
        // 중복 검사 오류 시, 에러 처리 로직
        catch (DataAlreadyExistsException | PasswordCheckFailedException ex) {
            bindingResult.reject("errorMessage", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());

            return "members/passwordUpdate";
        }

        return "redirect:/";
    }

}
