package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Message;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
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
                .id(0L)
                .nickName("abc")
                .email("hjkwon0814@navercom")
                .password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .build();


    }

    @Test
    public void setMessageInfo() {

        Message message = new Message.MessageBuilder()
                .member_Sender(Member.builder()
                        .id(0L)
                        .nickName("abc")
                        .email("hjkwon0814@navercom")
                        .password("1234")
                        .accountTypeEnum(AccountType.ADMISTRATOR)
                        .phoneNumber("010-1234-1234")
                        .loginTypeEnum(LoginType.EMAIL)
                        .ReportCount(0).build())
                .member_Receiver(Member.builder()
                        .id(0L)
                        .nickName("cba")
                        .email("hjkwon0814@navercom")
                        .password("1234")
                        .accountTypeEnum(AccountType.ADMISTRATOR)
                        .phoneNumber("010-1234-1234")
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