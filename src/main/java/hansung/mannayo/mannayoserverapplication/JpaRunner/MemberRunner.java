package hansung.mannayo.mannayoserverapplication.JpaRunner;

import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Transactional
public class MemberRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;


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

        Board board = Board.builder()
                .member(member)
                .title("title")
                .contents("hi")
                .isVote(true)
                .type(BoardType.TODAT_EAT_BOARD)
                .build();

        Vote vote = Vote.builder()
                .Contents("hi")
                .build();

        board.addVote(vote);
        memberRepository.save(member);
        boardRepository.save(board);


    }
}
