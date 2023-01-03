package middleProjects.com.security.members;

import lombok.RequiredArgsConstructor;
import middleProjects.com.entity.Member;
import middleProjects.com.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException("사용자 정보가 없습니다.")); // 이 부분 익셉션핸들러 만들기
        return MemberDetails.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities(member.getRole().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                        .collect(Collectors.toList()))
                .member(member)
                .build();
    }

}