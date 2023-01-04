package middleProjects.com.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController {

    // 전체 유저 조회
    // 게시글 삭제 및 커맨트 삭제

}
