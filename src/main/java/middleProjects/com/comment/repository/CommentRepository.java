package middleProjects.com.comment.repository;

import middleProjects.com.board.entity.Board;
import middleProjects.com.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoardIdOrderByModDateDesc(Long boardId);
    Page<Comment> findAllByBoardId(Long boardId, Pageable pageable);

    List<Comment> findAllByBoard(Board board);

    Long deleteByBoard(Board board);


}
