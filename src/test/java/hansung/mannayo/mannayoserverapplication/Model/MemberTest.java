package hansung.mannayo.mannayoserverapplication.Model;

import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class MemberTest {
    @Test
    public void run(){
        Member member = new Member.MemberBuilder().build();

    }


}