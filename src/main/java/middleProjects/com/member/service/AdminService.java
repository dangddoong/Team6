package middleProjects.com.member.service;


import middleProjects.com.member.dto.info.ResponseDto;

import java.util.List;

public interface AdminService {

    // 과연 관리자는 어떤 기능을 해야할까?

    List<ResponseDto> getMemberList();

    void deleteMemberBoard(Long id); // 누구나 지울 수 있어야 함 -> 그래서 검증로직 필요 x, 이미 이 컨트롤러는 admin만 타고 올 수 있기에?

    void deleteMemberComment(Long id);


    // 기존의 게시물과 댓글은 사용자만 지우거나 수정할 수 있어야 한다. -> 어드민은 가능하다 (어떻게 권한으로 처리할 것인지. )
//    List<> getMemberDataList();


}
