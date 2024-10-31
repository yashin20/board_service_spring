package project.board_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.board_service.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    private String nickname;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    //작성 게시글 리스트
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    /*소셜 로그인시 이미 등록됭 회원의 수정날짜만 업데이트 하기 위함*/
    public Member updateUpdateAt() {
        this.onPrePersist();
        return this;
    }


    public Member(Long id, String username, String password, String nickname, String email, String phone, MemberRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public void update(MemberDto.Request dto) {
        if (dto.getPassword() != null) {
            this.password = dto.getPassword();
        }
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if (dto.getPhone() != null) {
            this.phone = dto.getPhone();
        }
    }
}
