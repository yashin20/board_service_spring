package project.board_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.board_service.dto.CommentDto;

@Entity
@Getter
@RequiredArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private Integer likes = 0;

    //content 수정 여부 추적 필드
    private boolean isContentUpdated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; //소속 게시글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //작성자

    public Comment(Long id, String content, Post post, Member member) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.member = member;
    }

    public void update(CommentDto.Request dto) {
        if (dto.getContent() != null && !dto.getContent().equals(this.content)) {
            this.content = dto.getContent();
            this.isContentUpdated = true; //content 변경 여부 추적
        }
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
