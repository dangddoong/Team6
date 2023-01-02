package middleProjects.com.service.member;

import middleProjects.com.dto.member.info.ResponseDto;
import middleProjects.com.dto.member.register.RegisterRequestDto;
import middleProjects.com.dto.member.register.RegisterResponseDto;
import middleProjects.com.entity.Member;

public interface MemberService {

    void register(RegisterRequestDto registerRequestDto); // 회원가입

    RegisterResponseDto login(RegisterRequestDto registerRequestDto); // 사용자 로그인

    void checkByMemberPassword(RegisterRequestDto registerRequestDto, Member member); // 사용자 비밀번호 확인

    void checkByMemberDuplicated(RegisterRequestDto registerRequestDto); // 사용자 중복 체크

    // 고민 1) 어떤 데이터를 던져서 확인하는 것이 좋을까? 단순하게 이렇게 id값을 던지는게 맞나? 아니면 username이 전달되어야 하나?
    ResponseDto memberInfo(Long id);

}
