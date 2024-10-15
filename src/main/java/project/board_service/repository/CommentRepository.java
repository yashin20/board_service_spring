package project.board_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
