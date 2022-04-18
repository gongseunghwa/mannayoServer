package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.CommentRepository;
import hansung.mannayo.mannayoserverapplication.Repository.CommentToCommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentToCommentTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentToCommentRepository commentToCommentRepository;

    @Test
    public void Test(){
        Member member = Member.builder()
                .nickName("aa")
                .Email("tmdhk502@naver.com")
                .Password("tmdghk9609!")
                .PhoneNumber("010-0202-0303")
                .Birth(LocalDate.now())
                .ReportCount(1)
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .loginTypeEnum(LoginType.EMAIL)
                .build();

        Board board = Board.builder()
                .member(member)
                .title("title")
                .contents("hi")
                .type(BoardType.ADVERTISE_BOARD)
                .build();

        Board savedBoard1 = boardRepository.save(board);


        Comment comment = Comment.builder()
                .board(savedBoard1)
                .nickName("seunghwa")
                .build();

        commentRepository.save(comment);

        CommentToComment c2c = CommentToComment.builder()
                .comment(comment)
                .nickName("aaa")
                .contents("hi")
                .build();

        commentToCommentRepository.save(c2c);

    }



}