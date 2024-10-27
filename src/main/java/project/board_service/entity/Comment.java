package project.board_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.board_service.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * 대댓글을 위한 필드
     **/
    /*부모 댓글*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent; //부모 댓글

    /*대댓글 리스트*/
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();  // 대댓글 리스트

    //대댓글 추가 메서드
    public void  addReply(Comment reply) {
        replies.add(reply);
        reply.setParent(this);
    }
    //부모 댓글 유효성 검사 : 대댓글은 부모 댓글만 가질 수 있음.
    public void setParent(Comment parent) {
        if (parent != null && parent.getParent() != null) {
            throw new IllegalArgumentException("parent already exists");
        }
        this.parent = parent;
    }



    public Comment(Long id, String content, Post post, Member member, Comment parent) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.member = member;
        this.parent = parent;
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
