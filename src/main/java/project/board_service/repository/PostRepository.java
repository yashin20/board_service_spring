package project.board_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
