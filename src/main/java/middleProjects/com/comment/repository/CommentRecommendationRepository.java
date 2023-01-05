package middleProjects.com.comment.repository;

import middleProjects.com.board.entity.Board;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.entity.CommentRecommendation;
import middleProjects.com.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRecommendationRepository extends JpaRepository<CommentRecommendation, Long> {

    Optional<CommentRecommendation> findByMemberAndCommentId(Member member, Long commentId);
    Long countByComment(Comment comment);
    void deleteAllByCommentId(Long commentId);
}
