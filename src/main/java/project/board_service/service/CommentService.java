package project.board_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.CommentDto;
import project.board_service.entity.Comment;
import project.board_service.entity.Member;
import project.board_service.entity.Post;
import project.board_service.exception.DataNotFoundException;
import project.board_service.exception.UnauthorizedAccessException;
import project.board_service.repository.CommentRepository;
import project.board_service.repository.MemberRepository;
import project.board_service.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    /** Create Comment **/
    @Transactional
    public Comment createComment(CommentDto.Request dto) {
        return commentRepository.save(dto.toEntity());
    }
    /**대댓글 생성 메서드 : Depth <= 1
     * dto.getParent != null **/
    public Comment createReply(CommentDto.Request dto) {
        if (dto.getParent().getParent() != null) {
            throw new IllegalArgumentException("대댓글의 대댓글은 허용되지 않습니다.");
        }
        Comment comment = dto.toEntity();
        comment.setParent(comment.getParent());
        return commentRepository.save(comment);
    }

    /** Read Comment **/
    /*ID 기반 Comment 단건 조회*/
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found"));
    }

    //부모댓글과 대댓글을 조회
    public List<Comment> findCommentsByPost(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }


    /** Update Comment **/
    @Transactional
    public Comment updateComment(CommentDto.Request dto) {
        //1. 수정 대상 Comment
        Comment comment = findCommentById(dto.getId());

        //2. '작성자 == 로그인 회원' 확인
        if (!isAuthorValidation(comment)) {
            throw new UnauthorizedAccessException("commentService.updateComment - 올바르지 않은 접근");
        }

        //3. comment 수정
        comment.update(dto);
        return comment;
    }

    /** Delete Comment **/
    @Transactional
    public Long deleteComment(Long id) {
        //1. 삭제 대상 Comment
        Comment comment = findCommentById(id);
        Long deletedId = comment.getId();

        //2. '작성자 == 로그인 회원' 확인
        if (!isAuthorValidation(comment)) {
            throw new UnauthorizedAccessException("commentService.deleteComment - 올바르지 않은 접근");
        }

        //3. comment 삭제
        commentRepository.delete(comment);
        return deletedId;
    }

    //'작성자' == '로그인 회원' 검증
    private Boolean isAuthorValidation(Comment comment) {
        //현재 로그인 회원
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("PostService.isAuthorValidation - 존재하지 않는 회원"));

        return comment.getMember().getId().equals(member.getId());
    }


}
