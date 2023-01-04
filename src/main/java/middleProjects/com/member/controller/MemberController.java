package middleProjects.com.member.controller;

import lombok.RequiredArgsConstructor;
import middleProjects.com.board.dto.BoardResponseDto;

import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.dto.login.LoginRegisterDto;
import middleProjects.com.member.dto.register.RegisterRequestDto;
import middleProjects.com.member.dto.register.RegisterResponseDto;
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

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("/register") // 등록
    public HttpStatus register(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        memberServiceImpl.register(registerRequestDto);
        return HttpStatus.CREATED;
    }

    @PostMapping("/login") // 로그인
     public ResponseEntity<RegisterResponseDto> login(@RequestBody LoginRegisterDto loginRegisterDto){
        RegisterResponseDto data = memberServiceImpl.login(loginRegisterDto);
        return ResponseEntity.status(200).body(data);
    }

    /**
     * getMyInfo를 만들면서 무수히 많은 고민을 했다. 이 고민을 주석에 담았다.*
     // 이 메서드를 구현하면서 엄청나게 많은 생각을 했다.
     // 나는 단지 이 사용자를 조회할 때 사용자가 작성한 게시물과 댓글을 다 불러오고 싶었다.
     // (근데 만약에 이 데이터가 무수히 많은 데이터라면? 나는 단지 내 정보를 요청했는데, 작성한 게시물과 댓글이 많아져서 서버가 터져버리면?)
     // 그래서 생각했다. memberinfo 조회 시 그냥 게시글만 불러 오는 것으로.. -> 근데 나는 사용자의 정보를 요청한 것인데, 이 입장에서 게시물을 불러오는게 과연 정당할까?
     // 그리고 memberinfo 조회 시 파라미터로 사용자 정보만 전달된다. 또한 연관 관계조차 맺어져 있지 않다(다대일 단방향만 설정)
     // 양방향이면 그냥 쉽게 조회가 가능하겠지만, 앙뱡향이 아니라서 데이터를 어떤 한 공간에 다 넣어줘야한다. 이렇게 번거로운 작업을 해야하나?
     // + 코드가 띠용하고 떠오르지 않았다.. 이러한 굴례 속에서 생각이 많아졌고, 결국 방식을 철회하게 되었다.
     // 사용자는 단지 사용자의 정보를 원한 것이니까...
     */
    @GetMapping("/info/me") // 내 정보
    public ResponseEntity<ResponseDto> getMyInfo(@AuthenticationPrincipal MemberDetails memberDetails){
        ResponseDto data = memberServiceImpl.memberInfo(memberDetails.getMember().getId());
        return ResponseEntity.status(200).body(data);
    }

    @GetMapping("/info/myBoard") // 내가 작성한 board 불러오기
    public ResponseEntity<List<BoardResponseDto>> getMyBoardInfo(@AuthenticationPrincipal MemberDetails memberDetails){
        return ResponseEntity.status(200).body(memberServiceImpl.getMyBoard(memberDetails.getMember().getUsername()));
    }

}
