package project.board_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Member;
import project.board_service.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    //All Post List
    Page<Post> findAll(Pageable pageable);

    //조건 : member
    Page<Post> findByMember(Member member, Pageable pageable);

    //조건 : title keyword
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}