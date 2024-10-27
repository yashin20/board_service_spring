package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.board_service.exception.DataNotFoundException;
import project.board_service.service.MemberService;
import project.board_service.service.PostLikesService;

@RestController
@RequestMapping("/api/post-likes")
@RequiredArgsConstructor
public class PostLikesController {

    private final PostLikesService postLikesService;
    private final MemberService memberService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> toggleLike(@PathVariable Long postId) {
        try {
            /*현재 로그인된 회원 - comment 간의 좋아요*/
            postLikesService.toggleLike(memberService.getCurrentMember().getId(), postId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + postId + "번 게시글 좋아요 상태가 변경되었습니다.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"게시글 좋아요 변경 실패\"}");
        }
    }

}
