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

        Report report = Report.builder()
                .member(member)
                .board(board)
                .reportType(ReportType.BADNAME)
                .ReportReason("ahfjakdf")
                .ReportTime(LocalTime.now())
                .reportResult(ReportResultType.PERMENENT)
                .followUpAction(FollowUpActionType.COMPLETE)
                .build();

        memberRepository.save(member);
        boardRepository.save(board);
        reportRepository.save(report);



        assertEquals(report.getBoard().getId(),board.getId());
        assertEquals(report.getMember().getNickName(), member.getNickName());
        System.out.println(report.getBoard().getContents());




    }

}