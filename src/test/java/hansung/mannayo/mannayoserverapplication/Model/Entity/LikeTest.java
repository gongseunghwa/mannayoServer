package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.LikeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class LikeTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    public void insert(){
        Member member = new Member.MemberBuilder()
                .id(0L)
                .nickName("AAA")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();

        Board board = Board.builder()
                .member(member)
                .title("title")
                .contents("hi")
                .type(BoardType.ADVERTISE_BOARD)
                .build();

        Like like = Like.builder()
                .member(member)
                .board(board)
                .build();

    }

}