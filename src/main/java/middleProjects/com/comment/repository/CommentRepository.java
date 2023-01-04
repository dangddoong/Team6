package middleProjects.com.comment.repository;

import middleProjects.com.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // findByBoardId
    List<Comment> findAllByBoardIdOrderByModDateDesc(Long boardId);
}
