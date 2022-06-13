package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void commentTest(){
        Member member = Member.builder()
                .nickName("aa")
                .email("tmdhk502@naver.com")
                .password("tmdghk9609!")
                .phoneNumber("010-0202-0303")
                .birth(LocalDate.now())
                .ReportCount(1)
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .loginTypeEnum(LoginType.EMAIL)
                .build();

        Board board = Board.builder()
                .member(member)
                .contents("hi")
                .type(BoardType.ADVERTISE_BOARD)
                .build();

        Board savedBoard1 = boardRepository.save(board);


        Comment comment = Comment.builder()
                .board(savedBoard1)
                .nickName("seunghwa")
                .build();

        commentRepository.save(comment);
    }

}