package middleProjects.com.repository;

import middleProjects.com.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModifiedAtDesc();
}
