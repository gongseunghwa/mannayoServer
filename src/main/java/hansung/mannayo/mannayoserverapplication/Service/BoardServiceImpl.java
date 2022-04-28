package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public Optional<Board> findByMemberId(Long id) {
        return boardRepository.findByMember_Id(id);
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

}
