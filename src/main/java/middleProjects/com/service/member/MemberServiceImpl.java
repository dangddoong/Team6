package middleProjects.com.service.member;

import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.board.BoardResponseDto;
import middleProjects.com.dto.member.info.ResponseDto;
import middleProjects.com.dto.member.login.LoginRegisterDto;
import middleProjects.com.dto.member.register.RegisterRequestDto;
import middleProjects.com.dto.member.register.RegisterResponseDto;
import middleProjects.com.entity.Board;
import middleProjects.com.entity.Member;
import middleProjects.com.exception.member.MemberException;
import middleProjects.com.exception.member.MemberNicknameException;
import middleProjects.com.repository.BoardRepository;
import middleProjects.com.repository.MemberRepository;
import middleProjects.com.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterRequestDto registerRequestDto){
        checkByMemberDuplicated(registerRequestDto.getUsername());
        checkByMemberNickName(registerRequestDto.getNickName()); // 사실상 없어도 되는 기능이였지만 그냥 회원가입시 전달하는 데이터와 관련해서 중복 확인이 필요할 것이라 생각했음.
        Member member = registerRequestDto.toEntity(passwordEncoder.encode(registerRequestDto.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public void checkByMemberDuplicated(String username)throws RuntimeException{
        if(memberRepository.findByUsername(username).isPresent())
            throw new MemberException();}

    @Override
    public void checkByMemberNickName(String nickName) {
        if(memberRepository.findByNickName(nickName).isPresent()){
            throw new MemberNicknameException();
        }
    }

    @Override
    public ResponseDto memberInfo(Long id) {
        Member member =memberRepository.findById(id).orElseThrow(IllegalArgumentException::new); // 예외처리 필요함
//        List<BoardResponseDto> boardList = new ArrayList<>();
//        List<BoardResponseDto> data = boardRepository.findByMemberBoard(member.getUsername()).orElseThrow(()-> new IllegalArgumentException("게시물이 없습니다"));
//
//        data.forEach(board->boardList.add(BoardResponseDto.of(e)));
        return ResponseDto.of(member);
    }

    @Override
    @Transactional
    public RegisterResponseDto login(LoginRegisterDto loginRegisterDto) {
        Member member = memberRepository.findByUsername(loginRegisterDto.getUsername()).orElseThrow(IllegalArgumentException::new);
        checkByMemberPassword(loginRegisterDto, member);
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return RegisterResponseDto.of(member, jwtTokenProvider.createToken(member.getUsername()));
    }

    @Override
    public void checkByMemberPassword(LoginRegisterDto loginRegisterDto, Member member) {
        if(!passwordEncoder.matches(loginRegisterDto.getPassword(), member.getPassword()))
            throw new RuntimeException(" 비밀번호가 틀렸습니다 다시한번 확인해주세요.");
    }

    @Override
    public List<BoardResponseDto> getMyBoard(Long id) {
        List<Board> boardList = boardRepository.findAllByOrderByModDateDesc();
        return boardList.stream().map(BoardResponseDto::of).collect(Collectors.toList());
    }
}
