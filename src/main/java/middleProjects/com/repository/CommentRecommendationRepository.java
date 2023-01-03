package middleProjects.com.repository;

import middleProjects.com.entity.CommentRecommendation;
import middleProjects.com.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRecommendationRepository extends JpaRepository<CommentRecommendation, Long> {
    Optional<CommentRecommendation> findByMemberAndCommentId(Member member, Long commentId);
}
