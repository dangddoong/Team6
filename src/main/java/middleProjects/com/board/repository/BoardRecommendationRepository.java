package middleProjects.com.board.repository;

import middleProjects.com.board.entity.BoardRecommendation;
import middleProjects.com.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRecommendationRepository extends JpaRepository<BoardRecommendation,Long> {
    Optional<BoardRecommendation> findByMemberAndBoardId(Member member, Long boardId);
    Long countByBoardId(Long boardId);
}
