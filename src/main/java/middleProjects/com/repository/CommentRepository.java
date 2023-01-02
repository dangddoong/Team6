package middleProjects.com.repository;

import middleProjects.com.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // findByBoardId
    List<Comment> findAllByBoardIdOrderByModDateAtDesc(Long boardId);
}
