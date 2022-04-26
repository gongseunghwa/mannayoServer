package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.dto.BoardListRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;



public interface BoardService {

    //제목으로 게시글List 찾기
    List<Board> findByTitle(String title);

    //작성자 닉네임으로 게시글 List찾기
    Optional<List<Board>> findByMember(String nickName);

    List<Board> findAll();

}
