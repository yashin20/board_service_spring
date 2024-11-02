package project.board_service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
        @Size(max = 65535, message = "Content 는 최대 65535자까지 입력 가능합니다.")
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
        @Lob
        private String content;

        private Integer views;
        private Integer likes;
        //member - 작성자
        private String nickname;
        private String username;
        private Long memberId;

        private String createdAt;
        private String updatedAt;

        //좋아요 여부 확인
        private Boolean isLiked;

        //Entity -> Dto
        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent().replace("\n", "<br>");
            this.views = post.getViews();
            this.likes = post.getLikes();
            this.nickname = post.getMember().getNickname();
            this.username = post.getMember().getUsername();
            this.memberId = post.getMember().getId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
