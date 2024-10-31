package project.board_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board_service.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByEmail(String email);
}
