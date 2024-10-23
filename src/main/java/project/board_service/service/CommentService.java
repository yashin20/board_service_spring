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
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /** Create Comment **/
    @Transactional
    public Comment createComment(CommentDto.Request dto) {
        //1. post 주입
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new DataNotFoundException("Post not found"));
        dto.setPost(post);
        //2. member 주입
        if (dto.getMember() == null) {
            Member member = memberRepository.findById(dto.getMemberId())
                    .orElseThrow(() -> new DataNotFoundException("Member not found"));
            dto.setMember(member);
        }
        return commentRepository.save(dto.toEntity());
    }

    /** Read Comment **/
    /*ID 기반 Comment 단건 조회*/
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found"));
    }
    /*Post 기반 Comment 리스트 조회 - id DESC*/
    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findByPost(post, Sort.by(Sort.Direction.DESC, "id"));
    }

    /** Paging Comment List **/
    /*Post 기반 Comment 리스트 조회*/
//    public Page<Comment> findCommentsByPostPaging(Post post, Pageable pageable) {
//        return commentRepository.findByPost(post, pageable);
//    }

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
