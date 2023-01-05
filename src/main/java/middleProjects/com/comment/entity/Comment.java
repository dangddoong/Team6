package middleProjects.com.comment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import middleProjects.com.board.entity.BaseEntity;
import middleProjects.com.board.entity.Board;
import middleProjects.com.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIds", nullable = false)
    private Member member;

    // 내가 생각하기엔 대댓글은 comment가 존재해야 이 대댓글도 존재하기에 ManyToOne이 맞는것 같은데..
    // 어떠한 방식이 좋을까..
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="parent_id")
//    @OneToMany(mappedBy = "comment")
//    private List<Reply> commentList = new ArrayList<>();

    private Long parent;

    // 일반 게시글 작성
    public Comment(String contents, Board board, Member member ){
        this.contents = contents;
        this.board = board;
        this.member = member;
        this.parent = 0L;
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


    public Comment(String comment,Board board, Member member, Long commentParentId){
        this.contents = comment;
        this.board = board;
        this.member=member;
        this.parent=commentParentId;
    }

}
