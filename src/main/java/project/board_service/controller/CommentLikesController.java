package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.board_service.exception.DataNotFoundException;
import project.board_service.service.CommentLikesService;
import project.board_service.service.MemberService;

@Controller
@RequestMapping("/comment-likes")
@RequiredArgsConstructor
public class CommentLikesController {

    private final CommentLikesService commentLikesService;
    private final MemberService memberService;

    @PostMapping("/{commentId}/toggle/{postId}")
    public String toggleLike(@PathVariable Long commentId, RedirectAttributes redirectAttributes,
                             @PathVariable Long postId) {
        try {
            /*현재 로그인된 회원 - comment 간의 좋아요*/
            commentLikesService.toggleLike(memberService.getCurrentMember().getId(), commentId);
            redirectAttributes.addFlashAttribute("message", "댓글 좋아요 상태가 변경되었습니다.");
        } catch (DataNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/posts/" + postId;
    }
}
