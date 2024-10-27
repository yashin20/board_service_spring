package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.board_service.dto.CommentDto;
import project.board_service.service.CommentService;
import project.board_service.service.MemberService;
import project.board_service.service.PostService;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;
    private final PostService postService;

    /**댓글 생성**/
    @PostMapping("/{postId}/new")
    public String createComment(@PathVariable Long postId, @ModelAttribute CommentDto.Request request) {
        request.setMember(memberService.getCurrentMember());
        request.setPost(postService.findPostById(postId));
        commentService.createComment(request);
        return "redirect:/posts/" + postId;
    }

    /**댓글 삭제**/
    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam Long postId) {
        commentService.deleteComment(commentId);
        // 삭제 후 해당 게시글로 리다이렉트
        return "redirect:/posts/" + postId;
    }
}
