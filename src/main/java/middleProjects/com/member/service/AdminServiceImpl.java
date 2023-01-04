package middleProjects.com.member.service;

import lombok.RequiredArgsConstructor;
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
import middleProjects.com.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
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
        Board board = boardRepository.findById(id).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
        // 사용자가 삭제할 board의 게시물을 들고와서 (파라미터로 넘어온 id값은 내가 지우고자하는 board)->
        // -> 우선  이 board가지고 comment List를 찾아야 한다
        List<Comment> commentList =commentRepository.findAllByBoard(board);
        commentList.forEach(data -> commentRecommendationRepository.findById(data.getId()));
        commentRepository.deleteByBoard(board);
        boardRepository.delete(board);
    }

    @Override
    @Transactional
    public void deleteMemberComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_IS_NOT_EXIST));
    }


}
