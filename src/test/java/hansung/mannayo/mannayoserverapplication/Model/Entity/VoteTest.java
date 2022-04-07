package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VoteTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void Test() {
        Member member = Member.builder()
                .NickName("aa")
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
                .isVote(true)
                .type(BoardType.TODAT_EAT_BOARD)
                .build();


        Vote vote = Vote.builder()
                .board(board)
                .Contents("chickenchicken")
                .Count(0)
                .build();



        Member savedMember = memberRepository.save(member);
        Vote savedVote = voteRepository.save(vote);

        Board savedBoard = boardRepository.save(board);

        assertEquals(vote.getBoard().getId(),board.getId());
        assertEquals(0, vote.getCount());



    }

}