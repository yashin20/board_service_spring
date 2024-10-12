package project.board_service.dto;

import lombok.Data;
import project.board_service.entity.Member;
import project.board_service.entity.MemberRole;

public class MemberDto {

    @Data
    public static class Request {
        private Long id;
        private String username;
        private String password;
        private String nickname;
        private String email;
        private String phone;
        private MemberRole role;

        private String password2;

        //DTO -> Entity
        public Member toEntity() {
            return new Member(
                    this.id,
                    this.username,
                    this.password,
                    this.nickname,
                    this.email,
                    this.phone,
                    this.role
            );
        }
    }

    @Data
    public static class Response {
        private Long id;
        private String username;
        private String password;
        private String nickname;
        private String email;
        private String phone;
        private MemberRole role;

        //Entity -> DTO
        public Response(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.password = member.getPassword();
            this.nickname = member.getNickname();
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.role = member.getRole();
        }
    }
}
