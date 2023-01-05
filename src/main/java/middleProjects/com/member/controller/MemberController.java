package middleProjects.com.member.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.BoardResponseDto;

import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.dto.login.LoginRegisterDto;
import middleProjects.com.member.dto.register.RegisterRequestDto;
import middleProjects.com.member.dto.register.RegisterResponseDto;
import middleProjects.com.member.service.MemberService;
import middleProjects.com.security.members.MemberDetails;
import middleProjects.com.member.service.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register") // 등록
    public HttpStatus register(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        memberService.register(registerRequestDto);
        return HttpStatus.CREATED;
    }

    @PostMapping("/login") // 로그인
     public ResponseEntity<RegisterResponseDto> login(@RequestBody LoginRegisterDto loginRegisterDto){
        RegisterResponseDto data = memberService.login(loginRegisterDto);
        return ResponseEntity.status(200).body(data);
    }

    @GetMapping("/info/me") // 내 정보
    public ResponseEntity<ResponseDto> getMyInfo(@AuthenticationPrincipal MemberDetails memberDetails){
        ResponseDto data = memberService.memberInfo(memberDetails.getMember().getId());
        return ResponseEntity.status(200).body(data);
    }

    @GetMapping("/info/myBoard") // 내가 작성한 board 불러오기
    public ResponseEntity<List<BoardResponseDto>> getMyBoardInfo(@AuthenticationPrincipal MemberDetails memberDetails){
        return ResponseEntity.status(200).body(memberService.getMyBoard(memberDetails.getMember().getUsername()));
    }

}
