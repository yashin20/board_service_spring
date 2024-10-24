package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.board_service.exception.DataNotFoundException;
import project.board_service.service.MemberService;
import project.board_service.service.PostLikesService;

@Controller
@RequestMapping("/post-likes")
@RequiredArgsConstructor
public class PostLikesController {

    private final PostLikesService postLikesService;
    private final MemberService memberService;

    @PostMapping("/{postId}/toggle")
    public String toggleLike(@PathVariable Long postId, RedirectAttributes redirectAttributes) {

        try {
            /*현재 로그인된 회원 - post 간의 좋아요*/
            postLikesService.toggleLike(memberService.getCurrentMember().getId(), postId);
            redirectAttributes.addFlashAttribute("message", "게시글 좋아요 상태가 변경되었습니다.");
        } catch (DataNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/posts/" + postId;
    }
}
