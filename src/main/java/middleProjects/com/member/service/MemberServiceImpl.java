package middleProjects.com.member.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.entity.Board;
import middleProjects.com.board.repository.BoardRepository;
import middleProjects.com.board.dto.BoardResponseDto;
import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.dto.login.LoginRegisterDto;
import middleProjects.com.member.dto.register.RegisterRequestDto;
import middleProjects.com.member.dto.register.RegisterResponseDto;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import middleProjects.com.member.entity.Member;
import middleProjects.com.member.repository.MemberRepository;
import middleProjects.com.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            throw new CustomException(ExceptionStatus.MEMBER_IS_EXIST);}

    @Override
    public void checkByMemberNickName(String nickName) {
        if(memberRepository.findByNickName(nickName).isPresent()){
            throw new CustomException(ExceptionStatus.MEMBERNICKNAME_IS_EXIST);
        }
    }

    @Override
    public ResponseDto memberInfo(Long id) {
        Member member =memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return ResponseDto.of(member);
    }

    @Override
    @Transactional
    public RegisterResponseDto login(LoginRegisterDto loginRegisterDto) {
        Member member = memberRepository.findByUsername(loginRegisterDto.getUsername()).orElseThrow(()->new CustomException(ExceptionStatus.MEMBER_IS_NOT_EXIST));
        checkByMemberPassword(loginRegisterDto, member);
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return RegisterResponseDto.of(member, jwtTokenProvider.createToken(member.getUsername()));
    }

    @Override
    public void checkByMemberPassword(LoginRegisterDto loginRegisterDto, Member member) {
        if(!passwordEncoder.matches(loginRegisterDto.getPassword(), member.getPassword()))
            throw new CustomException(ExceptionStatus.PASSWORD_WRONG_EXCEPTION);
    }

    @Override
    public List<BoardResponseDto> getMyBoard(String username) {
        List<BoardResponseDto> boardList = new ArrayList<>();
        List<Board> getMyBoards = boardRepository.findByMember(username).orElseThrow(()->new CustomException(ExceptionStatus.BOARD_IS_NOT_EXIST));
        getMyBoards.forEach(e-> boardList.add(BoardResponseDto.of(e)));
        return boardList;
    }

}
