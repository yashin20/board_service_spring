package project.board_service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.MemberDto;
import project.board_service.entity.MemberRole;

// Test Data 생성
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitUserService initUserService;


    @PostConstruct
    public void init() {
        initUserService.init();
    }


    @Component
    static class InitUserService {
        @PersistenceContext
        private EntityManager em;
        @Autowired
        private PasswordEncoder passwordEncoder;


        @Transactional
        public void init() {

            String username = "member1";
            String password = "1234";
            MemberDto.Request dto = new MemberDto.Request();
            dto.setUsername(username);
            dto.setPassword(passwordEncoder.encode(password));
            dto.setRole(MemberRole.USER);
            em.persist(dto.toEntity());
        }
    }
}
