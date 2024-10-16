package project.board_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import project.board_service.entity.Member;
import project.board_service.entity.MemberRole;

public class MemberDto {

    @Data
    public static class Request {

        public interface Create {} /*username, nickname, email, phone*/
        public interface Update {} /*id, nickname, email, phone*/
        public interface UpdatePassword {} /*id, password, newPassword, newPassword2*/

        @NotNull(groups = {Update.class, UpdatePassword.class}, message = "ID는 필수 입력 값입니다.")
        private Long id;

        @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", groups = Create.class,
                message = "아이디는 영문 대소문자, 숫자로 이루어진 4~20자리여야 합니다.")
        @NotBlank(groups = Create.class, message = "아이디는 필수 입력 값입니다.")
        private String username;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", groups = UpdatePassword.class,
                message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(groups = UpdatePassword.class, message = "비밀번호는 필수 입력 값입니다.")
        private String password;

        @NotBlank(groups = {Create.class, Update.class}, message = "닉네임은 필수 입력 값입니다.")
        private String nickname;

        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", groups = {Create.class, Update.class},
                message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(groups = {Create.class, Update.class}, message = "이메일은 필수 입력 값입니다.")
        private String email;

        @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", groups = {Create.class, Update.class},
                message = "전화번호 형식이 올바르지 않습니다.")
        @NotBlank(groups = {Create.class, Update.class}, message = "전화번호는 필수 입력 값입니다.")
        private String phone;
        private MemberRole role;

        //새로운 비밀번호 체크
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", groups = {Create.class, UpdatePassword.class},
                message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(groups = {Create.class, UpdatePassword.class}, message = "비밀번호는 필수 입력 값입니다.")
        private String newPassword;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", groups = {Create.class, UpdatePassword.class},
                message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(groups = {Create.class, UpdatePassword.class}, message = "비밀번호는 필수 입력 값입니다.")
        private String newPassword2;

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
        private String nickname;
        private String email;
        private String phone;
        private MemberRole role;

        //Entity -> DTO
        public Response(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.nickname = member.getNickname();
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.role = member.getRole();
        }
    }
}
