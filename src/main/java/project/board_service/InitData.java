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
import project.board_service.dto.CommentDto;
import project.board_service.dto.MemberDto;
import project.board_service.dto.PostDto;
import project.board_service.entity.Comment;
import project.board_service.entity.Member;
import project.board_service.entity.MemberRole;
import project.board_service.entity.Post;

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

            /*Member 10개 생성*/
            Member member1 = createMember("member1", "1q2w3e4r~!");
            Member member2 = createMember("member2", "1q2w3e4r~!");
            Member member3 = createMember("member3", "1q2w3e4r~!");
            for (int i = 4; i <= 10; i++) {
                createMember("member" + i, "1q2w3e4r~!");
            }

            /*게시글 생성*/
            for (int i = 1; i <= 51; i++) {
                createPost("title" + i , "content" + i, member1);
                createPost("Post Title" + i , "content" + i + i, member2);
                createPost(i + "게시글 입니다.", "게시글 내용입니다." + i, member3);
            }
            Post post1 = createPost("comment test post", "content-comment", member1);

            /*댓글 생성*/
            for (int i = 1; i <= 30; i++) {
                createComment("comment" + i, post1, member1);
            }

        }

        public Member createMember(String username, String password) {
            MemberDto.Request dto = new MemberDto.Request();
            dto.setUsername(username);
            dto.setPassword(passwordEncoder.encode(password));
            dto.setNickname("nic_" + username);
            dto.setEmail(username + "@gmail.com");
            dto.setPhone("010-1234-5678");
            dto.setRole(MemberRole.USER);
            Member member = dto.toEntity();
            em.persist(member);
            return member;
        }

        public Post createPost(String title, String content, Member member) {
            PostDto.Request dto = new PostDto.Request();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setMember(member);
            Post post = dto.toEntity();
            em.persist(post);
            return post;
        }

        public Comment createComment(String content, Post post, Member member) {
            CommentDto.Request dto = new CommentDto.Request();
            dto.setContent(content);
            dto.setPost(post);
            dto.setMember(member);
            Comment comment = dto.toEntity();
            em.persist(comment);
            return comment;
        }
    }
}
