package middleProjects.com.member.service;


import middleProjects.com.member.dto.info.ResponseDto;

import java.util.List;

public interface AdminService {

    // 과연 관리자는 어떤 기능을 해야할까?

    List<ResponseDto> getMemberList();

    void deleteMemberBoard(Long id); // 여기서는 누구나 지울 수 있어야 합니다.



    // 기존의 게시물과 댓글은 사용자만 지우거나 수정할 수 있어야 한다. -> 어드민은 가능하다 (어떻게 권한으로 처리할 것인지. )
//    List<> getMemberDataList();


}
