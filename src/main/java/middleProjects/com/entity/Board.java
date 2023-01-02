package middleProjects.com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import middleProjects.com.dto.board.CreateBoardRequestDto;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Member member;
//    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    //private List<Comment> comments = new ArrayList<>();

    private Long recommendCount;

    public Board(CreateBoardRequestDto createBoardRequestDto) {
        this.title = createBoardRequestDto.getTitle();
        this.content = createBoardRequestDto.getContent();
    }
}
