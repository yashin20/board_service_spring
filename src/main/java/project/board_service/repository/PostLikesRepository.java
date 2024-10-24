package project.board_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Member;
import project.board_service.entity.Post;
import project.board_service.entity.PostLikes;

import java.util.Optional;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {

    //member - post 간 좋아요 존재 여부
    Boolean existsByMemberAndPost(Member member, Post post);

    //member - post 간 좋아요 객체 검색
    Optional<PostLikes> findByMemberAndPost(Member member, Post post);

}
