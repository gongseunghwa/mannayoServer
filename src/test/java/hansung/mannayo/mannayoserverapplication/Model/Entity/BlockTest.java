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
        Member member1 = new Member.MemberBuilder()
                .id(0L)
                .NickName("AAA")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();


        Member member2 = new Member.MemberBuilder()
                .id(1L)
                .NickName("BBB")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();

        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        Block block = Block.builder()
                .member(member1)
                .target_member(member2)
                .build();

        Block savedBlock = blockRepository.save(block);

        assertThat(blockRepository.findById(savedBlock.getId()).get().getMember().getNickName()).isEqualTo(savedMember1.getNickName());
        assertThat(blockRepository.findById(savedBlock.getId()).get().getTarget_member().getNickName()).isEqualTo(savedMember2.getNickName());
    }


}