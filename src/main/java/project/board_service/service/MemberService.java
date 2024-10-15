package project.board_service.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.MemberDto;
import project.board_service.entity.Member;
import project.board_service.entity.MemberRole;
import project.board_service.exception.DataAlreadyExistsException;
import project.board_service.exception.DataNotFoundException;
import project.board_service.exception.PasswordCheckFailedException;
import project.board_service.repository.MemberRepository;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    /** Create Member
     * username, nickname, email, phone, newPassword, newPassword2 **/
    @Transactional
    public Member createMember(MemberDto.Request dto) {
        //1. 중복 검사 (username, email, phone)
        duplicateValidation(dto);

        //2. 'password' 이중 검사
        passwordDoubleCheck(dto);

        //3. password encoding
        String encoded = passwordEncoding(dto.getNewPassword());
        dto.setPassword(encoded);

        /**일단 ROLE == USER**/
        dto.setRole(MemberRole.USER);

        //4. create member
        return memberRepository.save(dto.toEntity());
    }
    /* 1. 중복 검사 (username, email, phone) */
    private void duplicateValidation(MemberDto.Request dto) {
        //username 중복 확인
        if (memberRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DataAlreadyExistsException("이미 존재하는 'username' 입니다.");
        }

        //email 중복 확인
        if (memberRepository.findByUsername(dto.getEmail()).isPresent()) {
            throw new DataAlreadyExistsException("이미 존재하는 'email' 입니다.");
        }

        //phone 중복 확인
        if (memberRepository.findByUsername(dto.getPhone()).isPresent()) {
            throw new DataAlreadyExistsException("이미 존재하는 'phone' 입니다.");
        }
    }
    /* 2. 'password' 이중 검사 */
    private void passwordDoubleCheck(MemberDto.Request dto) {
        if (!dto.getNewPassword().equals(dto.getNewPassword2())) {
            throw new PasswordCheckFailedException("비밀번호가 동일하지 않습니다.");
        }
    }
    /* 3. password encoding */
    private String passwordEncoding(String unencodedPassword) {
        return passwordEncoder.encode(unencodedPassword);
    }


    /** Read Member **/
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 회원 입니다."));
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 회원 입니다."));
    }


    /** Update Member
     * 1. 회원정보 업데이트 (id, password, nickname, email, phone)
     * 2. 비밀번호 수정 업데이트 (id, password, newPassword, newPassword2)
     * **/
    @Transactional
    public Member updateMember(MemberDto.Request dto) {
        /*수정 대상 회원*/
        Member member = findMemberById(dto.getId());

        /*비밀번호 수정 과정*/
        if (dto.getNewPassword() != null && dto.getNewPassword2() != null &&
                !dto.getNewPassword().isEmpty() && !dto.getNewPassword2().isEmpty()) {

            /*1. 현재 비밀번호가 올바른가?*/
            if (!authenticate(dto.getUsername(), dto.getPassword())) {
                throw new PasswordCheckFailedException("현재 비밀번호가 올바르지 않습니다.");
            }

            /*2. 새로운 비밀번호 이중 체크*/
            passwordDoubleCheck(dto);

            /*3. new password encoding*/
            dto.setPassword(passwordEncoding(dto.getNewPassword()));
        }

        member.update(dto);

        return member;
    }
    /*로그인 인증 로직*/
    private boolean authenticate(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 회원입니다."));

        return passwordEncoder.matches(password, member.getPassword());
    }


    /** Delete Member **/
    @Transactional
    public Long deleteMember(Member member) {
        Long memberId = member.getId();
        memberRepository.delete(member);
        return memberId;
    }

    /*현재 로그인된 회원 가져오기*/
    private Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 회원 입니다."));
    }
    
}
