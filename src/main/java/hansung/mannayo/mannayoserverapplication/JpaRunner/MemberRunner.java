package hansung.mannayo.mannayoserverapplication.JpaRunner;

import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
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
                .NickName("12345")
                .Email("hjkwon0814@naver.com")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.of(2022,1,1))
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();


        Member member1 = Member.builder()
                .NickName("123456789")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .build();

        Board board = Board.builder()
                .member(member)
                .title("title")
                .contents("hi")
                .isVote(true)
                .type(BoardType.TODAT_EAT_BOARD)
                .build();


        Vote vote = Vote.builder()
                .board(board)
                .Contents("chicken")
                .Count(0)
                .build();



        Session session = entityManager.unwrap(Session.class);
        session.save(member1);
        session.save(board);
        session.save(vote);
        session.save(restaurant);
        session.save(member);

    }
}
