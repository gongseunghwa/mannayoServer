package hansung.mannayo.mannayoserverapplication.JpaRunner;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Message;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Transactional
public class MemberRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress("123");
        restaurant.setName("Maxicana");
        restaurant.setRestaurant_type(Restaurant_Type.HANSIK);
        restaurant.setNumber("01051231545");
        restaurant.setOwner("chulsu");
        restaurant.setBusinessStartHours(LocalTime.of(10,00));
        restaurant.setBusinessEndHours(LocalTime.of(22,00));
        restaurant.setBusinessDayOff(LocalDate.of(2022,03,31));


        Member member = Member.builder()
                .NickName("abc")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .build();



        Session session = entityManager.unwrap(Session.class);
        session.save(restaurant);
        session.save(member);

    }
}
