package middleProjects.com.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.board.entity.Board;
import middleProjects.com.board.repository.BoardRecommendationRepository;
import middleProjects.com.board.repository.BoardRepository;
import middleProjects.com.comment.entity.Comment;
import middleProjects.com.comment.repository.CommentRecommendationRepository;
import middleProjects.com.comment.repository.CommentRepository;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import middleProjects.com.member.dto.info.ResponseDto;
import middleProjects.com.member.entity.Member;
import middleProjects.com.member.entity.RoleType;
import middleProjects.com.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentRecommendationRepository commentRecommendationRepository;
    private final BoardRecommendationRepository boardRecommendationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseDto> getMemberList() {
        List<Member> memberLists = memberRepository.findAll();
        List<ResponseDto> resultData = new ArrayList<>();
        memberLists.forEach(member -> resultData.add(ResponseDto.of(member)));
        return resultData;
    }

    @Override
    @Transactional
    public void deleteMemberBoard(Long id) {
//        Board board = boardRepository.findById(id).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
//        boardRecommendationRepository.deleteByBoard(board);
//        boardRepository.delete(board); // 정당한 방식이 아니라고 생각한다 -> 한 번 고민해보고, 한번 여쭤보자... (김지환), 단지 데이터를 지우기 위해 양방향으로?...

        Board board = boardRepository.findById(id).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        List<Comment> commentList = commentRepository.findAllByBoard(board);
        for(Comment comment : commentList){
            commentRecommendationRepository.deleteAllByCommentId(comment.getId());
        }
        boardRecommendationRepository.deleteByBoard(board);
        boardRepository.delete(board);
        /**
         // 보류 ....
         //        List<Comment> commentList = commentRepository.findAllByBoard(board); // 해당 게시물에 댓글 찾아오기. // 이걸 리스트로 뽑아오는게 맞나?
         //        commentList.forEach(data -> log.info(data.getId().toString())); // 해당 게시물 몇번인지 확인
         //        log.info("헝그리정신");
         //        commentList.forEach(data -> commentRecommendationRepository.deleteByComment(data));
         //        log.info("기미상궁");
         //        commentRepository.deleteByBoard(board);
         //        log.info("배고프다");
         //        boardRepository.delete(board);

         //        commentList.forEach(data -> commentRecommendationRepository.deleteByCommentId(data.getId())); // 이제 해당 게시물들에 붙은 좋아요를 지워야 함.
         */

    }

    @Override
    @Transactional
    public void deleteMemberComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        commentRecommendationRepository.deleteByComment(comment);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void addRole(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않은 사용자입니다"));
        member.addRole(RoleType.ROLE_ADMIN);
        memberRepository.save(member);
    }

}
