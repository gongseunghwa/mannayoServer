package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class MessageTest {

    @Test
    public void test() {
        Member sender = Member.builder()
                .NickName("aa")
                .Email("tmdghk502@naver.com")
                .Password("password")
                .accountTypeEnum(AccountType.MEMBER)
                .PhoneNumber("010-9213-4579")
                .Birth(LocalDate.now())
                .loginTypeEnum(LoginType.EMAIL)
                .ReportCount(0)
                .build();


        Member receiver = Member.builder()
                .NickName("bb")
                .Email("tmdghk502@naver.com")
                .Password("password")
                .accountTypeEnum(AccountType.MEMBER)
                .PhoneNumber("010-9213-4579")
                .Birth(LocalDate.now())
                .loginTypeEnum(LoginType.EMAIL)
                .ReportCount(0)
                .build();

        Message message = Message.builder()
                .member_Receiver(receiver)
                .member_Sender(sender)
                .title("hi")
                .Date(LocalDate.now())
                .contents("sdsd")
                .isRead(true)
                .build();
    }
}