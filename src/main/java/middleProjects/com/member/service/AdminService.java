package middleProjects.com.member.service;


import middleProjects.com.member.dto.info.ResponseDto;

import java.util.List;

public interface AdminService {

    List<ResponseDto> getMemberList(); // 사용자 list 불러오기

    void deleteMemberBoard(Long id); // 사용자 게시글 삭제

    void deleteMemberComment(Long id); // 사용자 comment 삭제

    void addRole(Long userId); // 사용자 권한 부여



}
