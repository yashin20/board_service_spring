package project.board_service.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.board_service.entity.Comment;
import project.board_service.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findByPostId(Long postId); // 특정 게시글의 댓글 목록 조회

    //특정 게시글의 댓글 / 대댓글 구분없이 모든 댓글을 조회
    @Query("""
    SELECT c FROM Comment c
    LEFT JOIN FETCH c.parent p
    WHERE c.post.id = :postId
    ORDER BY p.id ASC NULLS FIRST, c.createdAt ASC
    """)
    List<Comment> findAllByPostId(@Param("postId") Long postId);
}
