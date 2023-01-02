package middleProjects.com.service.member;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.member.info.ResponseDto;
import middleProjects.com.dto.member.register.RegisterRequestDto;
import middleProjects.com.dto.member.register.RegisterResponseDto;
import middleProjects.com.entity.Member;
import middleProjects.com.repository.MemberRepository;
import middleProjects.com.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterRequestDto registerRequestDto){
        checkByMemberDuplicated(registerRequestDto);
        Member member = registerRequestDto.toEntity(passwordEncoder.encode(registerRequestDto.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public void checkByMemberDuplicated(RegisterRequestDto registerRequestDto)throws RuntimeException{
        if(memberRepository.findByUsername(registerRequestDto.getUsername()).isPresent())
            throw new RuntimeException("이미 존재하는 사용자입니다");}

    @Override
    public ResponseDto memberInfo(Long id) {
        Member member =memberRepository.findById(id).orElseThrow(RuntimeException::new); // 예외처리 필요함
        return ResponseDto.of(member);
    }

    @Override
    @Transactional
    public RegisterResponseDto login(RegisterRequestDto registerRequestDto) {
        Member member = memberRepository.findByUsername(registerRequestDto.getUsername()).orElseThrow(RuntimeException::new);
        checkByMemberPassword(registerRequestDto, member);
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return RegisterResponseDto.of(member, jwtTokenProvider.createToken(member.getUsername()));
    }

    @Override
    public void checkByMemberPassword(RegisterRequestDto registerRequestDto, Member member) {
        if(!passwordEncoder.matches(registerRequestDto.getPassword(), member.getPassword()))
            throw new RuntimeException("비밀번호가 틀렸습니다 다시한번 확인해주세요.");
    }


}
