package hansung.mannayo.mannayoserverapplication.Model;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReviewTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void ReviewTest(){
        Review review =  Review.builder()
                .build();

    entityManager.persist(review);


        assertThat(review.getWriteDate()).isBefore(LocalDateTime.now());

        System.out.println("=====================");
        System.out.println(review.getWriteDate());
        System.out.println("=====================");

    }

}