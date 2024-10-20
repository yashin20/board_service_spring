package project.board_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.CommentDto;
import project.board_service.entity.Comment;
import project.board_service.entity.Post;
import project.board_service.exception.DataNotFoundException;
import project.board_service.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    /** Create Comment **/
    @Transactional
    public Comment createComment(CommentDto.Request dto) {
        return commentRepository.save(dto.toEntity());
    }

    /** Read Comment **/
    /*ID 기반 Comment 단건 조회*/
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found"));
    }
    /*Post 기반 Comment 리스트 조회*/
    public List<Comment> findCommentsByPost(Post post) {
        return post.getComments();
    }

    /** Update Comment **/
    @Transactional
    public Comment updateComment(CommentDto.Request dto) {
        //1. 수정 대상 Comment
        Comment comment = findCommentById(dto.getId());
        //2. comment 수정
        comment.update(dto);
        return comment;
    }

    /** Delete Comment **/
    @Transactional
    public Long deleteComment(Long id) {
        //1. 삭제 대상 Comment
        Comment comment = findCommentById(id);
        Long deletedId = comment.getId();
        //2. comment 삭제
        commentRepository.delete(comment);
        return deletedId;
    }


}
