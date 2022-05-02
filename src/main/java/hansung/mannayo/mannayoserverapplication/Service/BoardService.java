package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;

import java.util.List;
import java.util.Optional;



public interface BoardService {

    //제목으로 게시글List 찾기
    Optional<List<Board>> findByTitle(String title);

    //작성자 닉네임으로 게시글 List찾기
    Optional<List<Board>> findByMember(String nickName);

    //작성자 id값으로 게시글 찾기
    Optional<List<Board>> findByMemberId(Long id);

    List<Board> findAll();

    Optional<Board> findById(Long id);

    Optional<List<Board>> findBoardByType(BoardType boardType);

    void updateImageAddress(Board board);
}
