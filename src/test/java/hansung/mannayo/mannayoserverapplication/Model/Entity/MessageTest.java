package hansung.mannayo.mannayoserverapplication.Model.Entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class MessageTest {



    @Test
    public void MessageTest() {
        Message message = new Message();

        message.setTitle("is it Working?");
        message.setContents("umm... I don't know");
        message.setDate(LocalDate.of(2022,3,31));
        message.setMember_Sender(memberRepository.getById("hjk"));
        message.setMember_Receiver((memberRepository.getById("hjkwon")));


        assertEquals(memberRepository.getById("hjk").getNickName(), message.getMember_Sender().getNickName());
        assertEquals(memberRepository.getById("hjkwon").getNickName(), message.getMember_Receiver().getNickName());
    }

}