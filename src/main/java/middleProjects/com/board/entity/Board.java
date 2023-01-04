package middleProjects.com.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.board.dto.UpdateBoardRequestDto;
import middleProjects.com.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="board_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    private Long recommendCount;

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;

    }
    /**
    // 김지환 주석 추가
    // board의 입장에서 사용자의 객체 데이터가 필요할까????
    // 계속 머릿속에 맴돈다... Member 객체를 가질지, String username을 가질지...

    // 1. 우선 코드의 양이 늘어난다.
    // 2. 객체지향적 관점에서 봤을 때, 객체들의 관계를 잘 이용하지 못한다. 우리가 JPA 쓰는 이유는 객체간 관계를 잘 활용하기 위해서지
    // 3. 그래 1, 2번 OK. 그럼 Board 입장에서는 username이 필요한거지 member 객체가 필요하냐 이 말인거지
    // 4. 연관관계 시 안정성을 확보하기 어렵다? (cacade = remove)
     */

    public void updateBoard(UpdateBoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }


    public void checkUser(Board board, String username) {
        if (!board.getMember().getUsername().equals(username)) throw new IllegalArgumentException("유저 불일치");
    }
}

