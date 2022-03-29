package hansung.mannayo.mannayoserverapplication.Model;

import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void run(){
        Member member = new Member.MemberBuilder()
                .NickName("abc")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .PhoneNumber("010-1234-1234")
                .Birth(LocalDate.of(2000,1,1))
                .ReportCount(0)
                .build();

        assertEquals(0, member.getReportCount());
    }


}