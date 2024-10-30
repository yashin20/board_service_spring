package project.board_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.board_service.entity.Comment;
import project.board_service.entity.Member;
import project.board_service.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {

    @Data
    public static class Request {

        public interface Create {} /*content, post, member*/
        public interface Update {} /*id, content*/

        @NotNull(groups = Update.class, message = "ID는 필수 입력 값입니다.")
        private Long id;

        @NotBlank(groups = {Create.class, Update.class}, message = "content 은 필수 입력 값입니다.")
        private String content;

        private Comment parent;
        private Post post;
        private Member member;

        //Dto -> Entity
        public Comment toEntity() {
            return new Comment(
                    this.id,
                    this.content,
                    this.post,
                    this.member,
                    this.parent
            );
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String content;
        private Integer likes;
        private Long postId;
        //작성자 - member
        private Long memberId;
        private String username;
        private String nickname;

        private String createdAt;
        private String updatedAt;

        //댓글 수정 여부 확인
        private Boolean isContentUpdated;
        //좋아요 여부 확인
        private Boolean isLiked;

        private List<Response> replies = new ArrayList<>();

        //Entity -> Dto
        public Response(Comment comment) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.postId = comment.getPost().getId();

            this.memberId = comment.getMember().getId();
            this.username = comment.getMember().getUsername();
            this.nickname = comment.getMember().getNickname();

            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();

            this.isContentUpdated = comment.isContentUpdated();
        }
    }
}
