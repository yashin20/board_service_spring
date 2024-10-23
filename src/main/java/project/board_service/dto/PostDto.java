package project.board_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.board_service.entity.Member;
import project.board_service.entity.Post;

public class PostDto {

    @Data
    public static class Request {

        public interface Create {} /*title, content, member*/
        public interface Update {} /*id, title, content*/

        @NotNull(groups = Update.class, message = "ID는 필수 입력 값입니다.")
        private Long id;

        @NotBlank(groups = {Create.class, Update.class}, message = "Title 은 필수 입력 값입니다.")
        private String title;

        @NotBlank(groups = {Create.class, Update.class}, message = "Content 는 필수 입력 값입니다.")
        private String content;

        //member - 작성자
        private Member member;

        //DTO -> Entity
        public Post toEntity() {
            return new Post(
                    this.id,
                    this.title,
                    this.content,
                    this.member
            );
        }
    }

    @Data
    public static class Response {

        private Long id;
        private String title;
        private String content;

        private Integer views;
        private Integer likes;
        //member - 작성자
        private String nickname;
        private Long memberId;

        private String createdAt;
        private String updatedAt;

        //Entity -> Dto
        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.views = post.getViews();
            this.likes = post.getLikes();
            this.nickname = post.getMember().getNickname();
            this.memberId = post.getMember().getId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
