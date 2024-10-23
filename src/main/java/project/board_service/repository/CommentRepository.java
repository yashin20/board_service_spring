package project.board_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Comment;
import project.board_service.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    Page<Comment> findByPost(Post post, Pageable pageable);
    List<Comment> findByPost(Post post, Sort sort);
}
