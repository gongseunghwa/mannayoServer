package hansung.mannayo.mannayoserverapplication.Model;

import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void setMemberInfo(){
        Member member = new Member.MemberBuilder()
                .NickName("abc")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .build();


    }

    @Test
    public void setMessageInfo() {

        Message message = new Message.MessageBuilder()
                .member_Sender(Member.builder().NickName("abc")
                        .Email("hjkwon0814@navercom")
                        .Password("1234")
                        .accountTypeEnum(AccountType.ADMISTRATOR)
                        .PhoneNumber("010-1234-1234")
                        .loginTypeEnum(LoginType.EMAIL)
                        .ReportCount(0).build())
                .member_Receiver(Member.builder().NickName("cba")
                        .Email("hjkwon0814@navercom")
                        .Password("1234")
                        .accountTypeEnum(AccountType.ADMISTRATOR)
                        .PhoneNumber("010-1234-1234")
                        .loginTypeEnum(LoginType.EMAIL)
                        .ReportCount(0).build())
                .title("hi")
                .contents("hihihi")
                .isRead(false)
                .Date(LocalDate.of(2000,1,1))
                .build();

        assertEquals("abc", message.getMember_Sender().getNickName());
        assertEquals("cba", message.getMember_Receiver().getNickName());
        assertEquals("hi", message.getTitle());
        assertEquals("hihihi", message.getContents());

    }


}