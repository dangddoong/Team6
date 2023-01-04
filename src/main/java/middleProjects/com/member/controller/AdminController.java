package middleProjects.com.member.controller;


import lombok.RequiredArgsConstructor;
import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.service.AdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminServiceImpl adminServiceImpl;
    // 전체 유저 조회 -> 기능 구현 중 -> 완료
    // 게시글 삭제 및 커맨트 삭제
    // 유저 권한 부여? -> 어떻게 해야할까? -> 어떻게 하면 권한을 이중으로 줄 수 있을까???

    @GetMapping("/userList") // 전체 유저 조회
    public ResponseEntity<List<ResponseDto>> getMemberList() {
        List<ResponseDto> data = adminServiceImpl.getMemberList();
        return ResponseEntity.status(200).body(data);
    }

    @DeleteMapping("/deleteBoard/{id}") // 삭제
    public HttpStatus deleteMemberBoard(@PathVariable Long id) {
        adminServiceImpl.deleteMemberBoard(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteComment/{id}")
    public HttpStatus deleteMemberComment(@PathVariable Long id){
        adminServiceImpl.deleteMemberComment(id);
        return HttpStatus.OK;
    }

}
