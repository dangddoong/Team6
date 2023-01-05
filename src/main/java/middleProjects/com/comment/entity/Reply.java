//package middleProjects.com.comment.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import middleProjects.com.board.entity.BaseEntity;
//import middleProjects.com.member.entity.Member;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Reply extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="reply_id")
//    private Long id;
//
//    private Long depth;
//
//    private String text;
//
//    @ManyToOne
//    @JoinColumn(name="comment_id")
//    private Comment comment;
//
//    @ManyToOne
//    @JoinColumn(name="member_id")
//    private Member member;
//
//
//    public Reply(Long depth, String text, Comment comment, Member member) {
//        this.depth = depth;
//        this.text = text;
//        this.comment = comment;
//        this.member = member;
//    }
//}
