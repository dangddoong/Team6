package middleProjects.com.member.service;


import middleProjects.com.member.dto.info.ResponseDto;

import java.util.List;

public interface AdminService {

    // 과연 관리자는 어떤 기능을 해야할까?
    // 기존의 게시물과 댓글은 사용자만 지우거나 수정할 수 있어야 한다. -> 어드민은 가능하다 (어떻게 권한으로 처리할 것인지. )

    List<ResponseDto> getMemberList(); // 사용자 list 불러오기

    void deleteMemberBoard(Long id); // 사용자 게시글 삭제

    void deleteMemberComment(Long id); // 사용자 comment 삭제

    void addRole(Long userId);



}
