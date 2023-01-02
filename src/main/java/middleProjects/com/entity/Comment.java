package middleProjects.com.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import middleProjects.com.dto.comment.CommentRequestDto;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private Long recommendCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Comment(String comment, Board board, Member member ){
        this.comment = comment;
        this.board = board;
        this.member = member;
        // board에 list보고 맞춰가야함. ex) board.getCommentList().add(this);
    }
}
