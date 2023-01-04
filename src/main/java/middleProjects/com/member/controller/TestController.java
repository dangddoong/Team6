package middleProjects.com.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String test1(){
        return "test";
    }

    @PostMapping("/ttest")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String test2(){
        return "test2";
    }

}
