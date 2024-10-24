package project.board_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.entity.Comment;
import project.board_service.entity.CommentLikes;
import project.board_service.entity.Member;
import project.board_service.exception.DataNotFoundException;
import project.board_service.repository.CommentLikesRepository;
import project.board_service.repository.CommentRepository;
import project.board_service.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    /* member - comment 좋아요(likes) 로직*/
    @Transactional
    public void toggleLike(Long memberId, Long commentId) {
        //1. 대상 comment / member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataNotFoundException("Member not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("Comment not found"));

        /*댓글 좋아요 처리 로직
         * 1. 이미 좋아요를 누른 경우 -> 좋아요 취소
         * 2. 좋아요를 누르지 않은 경우 -> 좋아요 누름*/
        if (commentLikesRepository.existsByMemberAndComment(member, comment)) {
            CommentLikes commentLikes = commentLikesRepository.findByMemberAndComment(member, comment)
                    .orElseThrow(() -> new DataNotFoundException("Comment Like not found"));
            commentLikesRepository.delete(commentLikes);
            comment.decrementLikes();
        } else {
            CommentLikes commentLikes = new CommentLikes();
            commentLikes.setMember(member);
            commentLikes.setComment(comment);
            commentLikesRepository.save(commentLikes);
            comment.incrementLikes();
        }
    }

    /*member - comment 이미 좋아요를 눌렀는지 확인하는 로직*/
    public boolean isLikedByMember(Long memberId, Long commentId) {
        //1. 대상 comment / member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataNotFoundException("Member not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("Comment not found"));

        return commentLikesRepository.existsByMemberAndComment(member, comment);
    }
}
