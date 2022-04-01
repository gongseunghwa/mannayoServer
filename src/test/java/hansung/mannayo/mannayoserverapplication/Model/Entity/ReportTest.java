package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.*;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReportTest {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void Test() {
        Member member = new Member.MemberBuilder()
                .NickName("abc")
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

        Report report = Report.builder()
                .member(member)
                .board(board)
                .reportType(ReportType.BADNAME)
                .ReportReason("ahfjakdf")
                .Reporttime(LocalTime.now())
                .reportResult(ReportResultType.PERMENENT)
                .followUpAction(FollowUpActionType.COMPLETE)
                .build();

        memberRepository.save(member);
        boardRepository.save(board);
        reportRepository.save(report);



        assertEquals(report.getBoard().getId(),board.getId());
        assertEquals(report.getMember().getNickName(), member.getNickName());
        System.out.println(report.getBoard().getTitle());
        System.out.println(report.getBoard().getContents());




    }

}