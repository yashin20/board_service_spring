package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.board_service.service.CommentLikesService;
import project.board_service.service.MemberService;

@RestController
@RequestMapping("/api/comment-likes")
@RequiredArgsConstructor
public class CommentLikesController {

    private final CommentLikesService commentLikesService;
    private final MemberService memberService;

    @PostMapping("/{commentId}")
    public ResponseEntity<?> toggleLike(@PathVariable Long commentId) {
        try {
            /*현재 로그인된 회원 - comment 간의 좋아요*/
            commentLikesService.toggleLike(memberService.getCurrentMember().getId(), commentId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + commentId + "번 댓글 좋아요 상태가 변경되었습니다.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"댓글 좋아요 변경 실패\"}");
        }
    }
}
