package middleProjects.com.controller.member;

import lombok.RequiredArgsConstructor;
//import middleProjects.com.dto.member.MemberInfoResponseDto;
import middleProjects.com.dto.member.info.ResponseDto;
import middleProjects.com.dto.member.register.RegisterRequestDto;
import middleProjects.com.dto.member.register.RegisterResponseDto;
import middleProjects.com.service.member.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody RegisterRequestDto registerRequestDto){
        memberServiceImpl.register(registerRequestDto);
        return HttpStatus.CREATED;
    }

    @PostMapping("/login")
     public ResponseEntity<RegisterResponseDto> login(@RequestBody RegisterRequestDto registerRequestDto){
        RegisterResponseDto data = memberServiceImpl.login(registerRequestDto);
        return ResponseEntity.status(200).body(data);
    }

    @GetMapping("/info/me/{id}")
    public ResponseEntity<ResponseDto> info(@PathVariable("id") Long id){
        ResponseDto data = memberServiceImpl.memberInfo(id);
        return ResponseEntity.status(200).body(data);
    }


}
