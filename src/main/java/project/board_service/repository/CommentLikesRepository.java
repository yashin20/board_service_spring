package project.board_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Comment;
import project.board_service.entity.CommentLikes;
import project.board_service.entity.Member;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    //member - comment 간 좋아요 존재 여부
    Boolean existsByMemberAndComment(Member member, Comment comment);

    //member - comment 간 좋아요 객체 검색
    Optional<CommentLikes> findByMemberAndComment(Member member, Comment comment);
}
