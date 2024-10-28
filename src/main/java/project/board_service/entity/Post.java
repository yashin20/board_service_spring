package project.board_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.board_service.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    @Lob
    @Column(name = "content", columnDefinition = "TEXT") // MySQL VARCHAR 최대 길이 (TEXT 유형 = 65,535)
    private String content;

    private Integer views = 0;
    private Integer likes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //소속 댓글 리스트
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(Long id, String title, String content, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(PostDto.Request dto) {
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }
    }

    //views increment
    public void incrementView() {
        this.views += 1;
    }

    //likes increment
    public void incrementLikes() {
        this.likes += 1;
    }

    //likes decrement
    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes -= 1;
        }
    }
}
