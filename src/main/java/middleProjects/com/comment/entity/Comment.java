package middleProjects.com.comment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import middleProjects.com.board.entity.BaseEntity;
import middleProjects.com.board.entity.Board;
import middleProjects.com.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIds", nullable = false)
    private Member member;

    public Comment(String contents, Board board, Member member ){
        this.contents = contents;
        this.board = board;
        this.member = member;
        // board에 list보고 맞춰가야함. ex) board.getCommentList().add(this);
    }
    public void memberAndCommentWriterEqualCheck(Long memberId){
        if(!this.member.getId().equals(memberId)){
            //TODO: 추후에 핸들링 할 수 있도록 exception 수정요망.
            throw new IllegalArgumentException("댓글작성자와 멤버 불일치");
        }
    }


    public void updateComment(String contents) {
        this.contents = contents;
    }
}
