package middleProjects.com.member.repository;

import middleProjects.com.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByNickName(String nickName);

    @Query("select m from Member m order by m.createDate desc ")
    List<Member> findAllByCreateDate();

}
