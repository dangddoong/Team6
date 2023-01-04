package middleProjects.com.comment.entity;

import lombok.NoArgsConstructor;
import middleProjects.com.member.entity.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CommentRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public CommentRecommendation(Comment comment, Member member) {
        this.member = member;
        this.comment = comment;
        this.comment.getCommentRecommendationList().add(this);
    }
}
