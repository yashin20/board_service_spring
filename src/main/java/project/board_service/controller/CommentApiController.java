package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.board_service.dto.CommentDto;
import project.board_service.entity.Comment;
import project.board_service.service.CommentService;
import project.board_service.service.MemberService;
import project.board_service.service.PostService;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final MemberService memberService;

    /**
     * 대댓글 생성 API
     */
    @PostMapping("/create-reply/{parentId}")
    public ResponseEntity<?> createReply(@PathVariable Long parentId, @RequestBody CommentDto.Request dto) {
        try {
            Comment parent = commentService.findCommentById(parentId);
            dto.setParent(parent);
            dto.setMember(memberService.getCurrentMember());
            dto.setPost(parent.getPost());
            commentService.createReply(dto);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + parent.getId() + "의 대댓글이 작성되었습니다.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"대댓글 작성 실패\"}");
        }
    }


    /**
     * 댓글 수정 API
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentDto.Request dto) {
        try {
            dto.setId(commentId);
            commentService.updateComment(dto);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + commentId + "댓글이 수정 되었습니다.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"댓글 수정 실패\"}");
        }
    }

    /**
     * 댓글 삭제 API
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId); // 댓글 삭제 호출
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"" + commentId + "번 댓글이 삭제되었습니다.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"댓글 삭제 실패\"}");
        }
    }
}
