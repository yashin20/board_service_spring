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

            String lyric1 = "멋지게 골인\n" +
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

            String lyric2 = "Baby (baby), got me looking so crazy (crazy)\n" +
                    "빠져버리는 daydream (daydream)\n" +
                    "Got me feeling you 너도 말해줄래?\n" +
                    "누가 내게 뭐라든 남들과는 달라 넌\n" +
                    "Maybe you could be the one (one)\n" +
                    "날 믿어봐 한번 I'm not looking for just fun\n" +
                    "Maybe I could be the one\n" +
                    "Oh, baby (baby)\n" +
                    "예민하대 나 lately (lately)\n" +
                    "너 없이는 매일 매일이 yeah (매일이 yeah)\n" +
                    "재미없어, 어쩌지?\n" +
                    "I just want you call my phone right now\n" +
                    "I just wanna hear, \"You're mine\"\n" +
                    "'Cause I know what you like boy (ah-ah)\n" +
                    "You're my chemical hype boy (ah-ah)\n" +
                    "내 지난날들은 눈 뜨면 잊는 꿈\n" +
                    "Hype boy 너만 원해\n" +
                    "Hype boy 내가 전해\n" +
                    "And we can go high\n" +
                    "말해봐 yeah 느껴봐 mm-mm\n" +
                    "Take him to the sky\n" +
                    "You know, I hype you, boy\n" +
                    "눈을 감아\n" +
                    "말해봐 yeah 느껴봐 mm-mm\n" +
                    "Take him to the sky\n" +
                    "You know, I hype you, boy\n" +
                    "잠에 들려고, 잠에 들려 해도\n" +
                    "니 생각에 또 새벽 세시 oh-oh\n" +
                    "알려줄 거야 they can't have you no more\n" +
                    "봐봐, 여기 내 이름 써있다고 yeah\n" +
                    "누가 내게 뭐라든 남들과는 달라 넌\n" +
                    "Maybe you could be the one\n" +
                    "날 믿어봐 한번 I'm not looking for just fun\n" +
                    "Maybe I could be the one\n" +
                    "Oh, baby (baby)\n" +
                    "예민하대 나 lately (lately)\n" +
                    "너 없이는 매일 매일이 yeah (매일이 yeah)\n" +
                    "재미없어, 어쩌지?\n" +
                    "I just want you call my phone right now\n" +
                    "I just wanna hear, \"You're mine\"\n" +
                    "'Cause I know what you like boy (ah-ah)\n" +
                    "You're my chemical hype boy (ah-ah)\n" +
                    "내 지난날들은 눈 뜨면 잊는 꿈\n" +
                    "Hype boy 너만 원해\n" +
                    "Hype boy 내가 전해\n" +
                    "And we can go high\n" +
                    "말해봐 yeah 느껴봐 mm-mm\n" +
                    "Take him to the sky\n" +
                    "You know, I hype you, boy\n" +
                    "눈을 감아\n" +
                    "말해봐 yeah 느껴봐 mm-mm\n" +
                    "Take him to the sky\n" +
                    "You know, I hype you, boy";

            String lyric3 = "Me and Kurt feel the same, too much pleasure is pain\n" +
                    "My girl spites me in vain, all I do is complain\n" +
                    "She needs something to change, need to take off the edge\n" +
                    "So - it all tonight\n" +
                    "And don't tell me to shut up\n" +
                    "When you know you talk too much\n" +
                    "But you don't got - to say\n" +
                    "I want you out of my head\n" +
                    "I want you out of my bedroom tonight\n" +
                    "There's no way I could save you\n" +
                    "'Cause I need to be saved, too\n" +
                    "I'm no good at goodbyes\n" +
                    "We're both acting insane, but too stubborn to change\n" +
                    "Now I'm drinkin' again, 80 proof in my veins\n" +
                    "And my fingertips stained, looking over the edge\n" +
                    "Don't - with me tonight\n" +
                    "Said you needed this heart then you got it\n" +
                    "Turns out that it wasn't what you wanted\n" +
                    "And we wouldn't let go and we lost it\n" +
                    "Now I'm a goner\n" +
                    "I want you out of my head\n" +
                    "I want you out of my bedroom tonight\n" +
                    "There's no way I could save you\n" +
                    "'Cause I need to be saved, too\n" +
                    "I'm no good at goodbyes\n" +
                    "I want you right in my life\n" +
                    "I want you back here tonight\n" +
                    "I'm tryna to cut you, no knife\n" +
                    "I wanna slice you and dice you\n" +
                    "My argues possessive, it got you precise\n" +
                    "Can you not turn off the TV? I'm watching the fight\n" +
                    "I flood the garage, blue diamond, no shark\n" +
                    "You're Barbie life doll, it's Nicki Minaj\n" +
                    "You don't need a key to drive, your car on the charger\n" +
                    "I just wanna see the side, the one that's unbothered\n" +
                    "And I don't want you never go outside\n" +
                    "I promise if they play, my - slidin'\n" +
                    "I'm - her and the tour bus still ridin'\n" +
                    "Yeah, yeah, yeah, yeah, yeah\n" +
                    "I want you out of my head\n" +
                    "I want you out of my bedroom tonight\n" +
                    "There's no way I can save you\n" +
                    "Because I need to be saved too\n" +
                    "I'm no good at goodbyes\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "I'm no good at goodbyes\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "Goodbye, goodbye, goodbye\n" +
                    "I'm no good at goodbyes";


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
            Post post1 = createPost("REALLY REALLY - WINNER 中", lyric1, member1);
            Post post2 = createPost("Hype Boy - New Jeans", lyric2, member2);
            Post post3 = createPost("Goodbyes - Post Malone", lyric3, member3);


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
