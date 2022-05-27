package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.dto.BoardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public Optional<List<Board>> findByMemberId(Long id) {
        return boardRepository.findByMember_Id(id);
    }

    @Override
    public Optional<List<Board>> findBoardByType(BoardType boardType) { // 게시판 타입 별 호출
        return boardRepository.findByType(boardType);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Optional<List<Board>> findByTitle(String title) {

        Optional<List<Board>> boardList  = boardRepository.findByTitleOrderByCreatedDateDesc(title);
        if(boardList.isPresent()){
            return boardList;
        }
        throw new EntityNotFoundException("Cannot find any board given title");
    }

    @Override
    public Optional<List<Board>> findByMember(String nickName) {
        Optional<List<Board>> boardList = boardRepository.findByMember_NickName(nickName);
        if(boardList.isPresent()) {
            return boardList;
        }

        throw  new EntityNotFoundException("Cannot find any board given nickName");
    }

    public List<Board> findAll(){
        List<Board> boardList = boardRepository.findAll();

        return boardList;
    }

    @Override
    public void updateImageAddress(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board insert(BoardRequest dto) {
        Member member = memberRepository.findById(dto.getMemberId()).get();
        Board board = dtoToEntity(dto);
        member.addBoard(board);
        return boardRepository.save(board);
    }

    private Board dtoToEntity(BoardRequest dto){
        Member member = memberRepository.findById(dto.getMemberId()).get();

        Board board = Board.builder()
                .member(member)
                .title(dto.getTitle())
                .contents(dto.getContents())
                .isVote(dto.getIsVote())
                .type(dto.getType())
                .build();
        return board;
    }
}
