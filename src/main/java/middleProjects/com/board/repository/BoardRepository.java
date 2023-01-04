package middleProjects.com.board.repository;

import middleProjects.com.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModDateDesc();

    @Query("select b from Board b join Member m on b.member.username=m.username where b.member.username=:username")
    Optional<List<Board>> findByMember(@Param("username") String username);
}
