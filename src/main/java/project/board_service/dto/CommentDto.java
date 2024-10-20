package project.board_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.board_service.entity.Comment;
import project.board_service.entity.Member;
import project.board_service.entity.Post;

public class CommentDto {

    @Data
    public static class Request {

        public interface Create {} /*content, post, member*/
        public interface Update {} /*id, content*/

        @NotNull(groups = Update.class, message = "ID는 필수 입력 값입니다.")
        private Long id;

        @NotBlank(groups = {Create.class, Update.class}, message = "content 은 필수 입력 값입니다.")
        private String content;

        private Post post;
        private Member member;

        //Dto -> Entity
        public Comment toEntity() {
            return new Comment(
                    this.id,
                    this.content,
                    this.post,
                    this.member
            );
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String content;
        private Integer likes;
        private Long postId;
        private Long memberId;

        //Entity -> Dto
        public Response(Comment comment) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.postId = comment.getPost().getId();
            this.memberId = comment.getMember().getId();
        }
    }
}
