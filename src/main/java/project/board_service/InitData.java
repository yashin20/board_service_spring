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

            String lyric = "멋지게 골인\n" +
                    "프러포즈 같은 세리머니 (oh yeah)\n" +
                    "정해보자 호칭\n" +
                    "허니 말고 자기 아님 달링\n" +
                    "낯간지럽네 상상해봐도\n" +
                    "긴장돼 필요해 a lot of alcohol\n" +
                    "덩치는 산만해도 네 앞에선 작아지네\n" +
                    "Oh 내 맘 알까나\n" +
                    "첫눈에 미끄러지듯이 falling in love\n" +
                    "내 맘을 훔친 범인인 건 바로 너\n" +
                    "너의 미모 질투해 여신도 (wow)\n" +
                    "내 심장의 떨림은 진동 brrr\n" +
                    "치명적인 매력이 날 killin'\n" +
                    "섹시해 날 자극하는 hot chili woo\n" +
                    "오해하지 마 나는 진짜 진심으로 채워줄게 너의 빈 잔\n" +
                    "지금 내 눈에 제일 아름다운 건 너야 lady\n" +
                    "널 향한 내 맘이 돈이면 아마 난 billionaire\n" +
                    "널 좋아해";


            /*Member 10개 생성*/
            Member member1 = createMember("member1", "1q2w3e4r~!");
            Member member2 = createMember("member2", "1q2w3e4r~!");
            Member member3 = createMember("member3", "1q2w3e4r~!");
            for (int i = 4; i <= 10; i++) {
                createMember("member" + i, "1q2w3e4r~!");
            }

            /*게시글 생성*/
            for (int i = 1; i <= 51; i++) {
                createPost("Hello, World" + i , "Hello! This is Content!" + i, member1);
                createPost("안녕하세요!" + i , "안녕하세요 반갑습니다!" + i, member2);
                createPost("게시글 입니다." + i, "게시글 내용입니다." + i, member3);
            }
            Post post1 = createPost("게시글 제목 입니다.",
                    lyric,
                    member1);


            Comment comment = createComment("부모댓글 입니다.", post1, member1);
            /*댓글 생성*/
            for (int i = 1; i <= 30; i++) {
                createComment("comment" + i, post1, member1);

                if (i % 3 == 0) {
                    createReply(comment, "member1 : 대댓글 입니다.", post1, member1);
                } else if (i % 5 == 0) {
                    createReply(comment, "member2 : 대댓글 입니다.", post1, member2);
                } else if (i % 7 == 0) {
                    createReply(comment, "member3 : 대댓글 입니다.", post1, member3);
                }
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

        public Comment createReply(Comment parent, String content, Post post, Member member) {
            CommentDto.Request dto = new CommentDto.Request();
            dto.setContent(content);
            dto.setPost(post);
            dto.setMember(member);
            dto.setParent(parent);
            Comment comment = dto.toEntity();
            em.persist(comment);
            return comment;
        }
    }
}
