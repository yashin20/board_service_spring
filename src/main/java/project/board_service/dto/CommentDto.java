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
        private Long postId;

        private Member member;
        private Long memberId;

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
        //작성자 - member
        private Long memberId;
        private String nickname;

        private String createdAt;
        private String updatedAt;

        //댓글 수정 여부 확인
        private Boolean isUpdated;

        //Entity -> Dto
        public Response(Comment comment) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.postId = comment.getPost().getId();

            this.memberId = comment.getMember().getId();
            this.nickname = comment.getMember().getNickname();

            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();

            if (!comment.getCreatedAt().equals(comment.getUpdatedAt())) {
                isUpdated = true;
            }
        }
    }
}
