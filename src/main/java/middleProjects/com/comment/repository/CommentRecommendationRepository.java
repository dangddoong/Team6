package middleProjects.com.comment.repository;

import middleProjects.com.board.entity.Board;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.entity.CommentRecommendation;
import middleProjects.com.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRecommendationRepository extends JpaRepository<CommentRecommendation, Long> {

    Optional<CommentRecommendation> findByMemberAndCommentId(Member member, Long commentId);

    Long countByCommentId(Long commentId);

    Long countByComment(Comment comment);

    Object deleteByCommentId(Long commentId);

    Long deleteByComment(Comment comment);

}
