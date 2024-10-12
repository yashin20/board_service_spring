package project.board_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.MemberDto;
import project.board_service.entity.Member;
import project.board_service.exception.DataAlreadyExistsException;
import project.board_service.exception.PasswordCheckFailedException;
import project.board_service.repository.MemberRepository;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /** Create Member **/
    @Transactional
    public Member createMember(MemberDto.Request dto) {
        //1. 중복 검사 (username, email, phone)
        duplicateValidation(dto);

        //2. 'password' 이중 검사
        passwordDoubleCheck(dto);

        //3. create member
        return memberRepository.save(dto.toEntity());
    }
    /* 1. 중복 검사 (username, email, phone) */
    public void duplicateValidation(MemberDto.Request dto) {
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
    public void passwordDoubleCheck(MemberDto.Request dto) {
        if (!dto.getPassword().equals(dto.getPassword2())) {
            throw new PasswordCheckFailedException("비밀번호가 동일하지 않습니다.");
        }
    }

}
