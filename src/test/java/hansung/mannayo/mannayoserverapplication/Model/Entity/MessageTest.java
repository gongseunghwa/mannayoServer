package hansung.mannayo.mannayoserverapplication.Model.Entity;


import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class MessageTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    public void MessageTest() {
//        Message message = new Message();
//
//        message.setTitle("is it Working?");
//        message.setContents("umm... I don't know");
//        message.setDate(LocalDate.of(2022,3,31));
//        message.setMember_Sender(memberRepository.getById("hjk"));
//        message.setMember_Receiver((memberRepository.getById("hjkwon")));
//
//
//        assertEquals(memberRepository.getById("hjk").getNickName(), message.getMember_Sender().getNickName());
//        assertEquals(memberRepository.getById("hjkwon").getNickName(), message.getMember_Receiver().getNickName());
    }

}