package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.BlockRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class BlockTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BlockRepository blockRepository;


    @Test
    public void run(){
        Member member1 = Member.builder()
                .nickName("AAA")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();


        Member member2 = Member.builder()
                .nickName("BBB")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();


        Block block = Block.builder()
                .target_member(member1)
                .member(member2)
                .build();

        Block savedBlock = blockRepository.save(block);

        assertThat(blockRepository.getById(savedBlock.getId()).getTarget_member().getNickName()).isEqualTo(block.getTarget_member().getNickName());

    }


}