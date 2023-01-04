package middleProjects.com.member.service;

import lombok.RequiredArgsConstructor;
import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.entity.Member;
import middleProjects.com.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;

    @Override
    public List<ResponseDto> getMemberList() {
        List<Member> memberLists = memberRepository.findAll();
        List<ResponseDto> resultData = new ArrayList<>();
        memberLists.forEach(member -> resultData.add(ResponseDto.of(member)));
        return resultData;
    }

}
