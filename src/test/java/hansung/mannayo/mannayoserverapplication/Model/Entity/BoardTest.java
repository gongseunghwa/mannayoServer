package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void boardTest(){
        Board board = Board.builder()
                .nickName("aa")
                .title("title")
                .contents("hi")
                .build();
    }


}