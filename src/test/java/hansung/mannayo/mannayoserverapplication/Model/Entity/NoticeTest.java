package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.NoticeType;
import hansung.mannayo.mannayoserverapplication.Repository.NoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class NoticeTest {
    @Autowired
    NoticeRepository noticeRepository ;

    @Test
    public void test(){
        Notice notice = Notice.builder()
                .type(NoticeType.AD)
                .sender("a")
                .receiver("b")
                .build();

        noticeRepository.save(notice);
    }

}