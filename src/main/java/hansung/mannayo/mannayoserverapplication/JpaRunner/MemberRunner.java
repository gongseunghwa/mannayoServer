package hansung.mannayo.mannayoserverapplication.JpaRunner;

import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.BlockRepository;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
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
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class MemberRunner implements ApplicationRunner {

    @Autowired
    BlockRepository blockRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRe;


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
                .nickName("12345")
                .Email("hjkwon0814@naver.com")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

        Member member2 = Member.builder()
                .nickName("seunghwa gong")
                .Email("hjkwon0814@naver.com")
                .Password("1234")
                .reviewList(new ArrayList<>())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .Birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();


        Review review = Review.builder().title("hi1").build();
        Review review1 = Review.builder().title("hi2").build();
//        List<Review> reviewList = new ArrayList<Review>();
//        reviewList.add(review);
//        reviewList.add(review1);
//        member2.setReviewList(reviewList);
//        review.setMember(member2);
//        review1.setMember(member2);



        member2.addReview(review);
        member2.addReview(review1);

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

        reviewRe.save(review);
        reviewRe.save(review1);
        memberRepository.save(member);
        memberRepository.save(member2);


    }
}
