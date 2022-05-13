package hansung.mannayo.mannayoserverapplication.JpaRunner;

import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    JjimRepository jjimRepository;

    @Autowired
    LikeRepository likeRepository;

    private final MenuRepository menuRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .address("123")
                .name("Naxicana")
                .type(Restaurant_Type.HANSIK)
                .number("01051231545")
                .owner("chulsu")
                .menuList(new ArrayList<>())
                .businessStartHours(LocalTime.of(10,00))
                .businessEndHours(LocalTime.of(22,00))
                .businessDayOff(LocalDate.of(2022,03,31))
                .reviewList(new ArrayList<>())
                .build();
        
        Member member = Member.builder()
                .realName("권혁진")
                .nickName("12345")
                .email("hjkwon0814@naver.com")
                .password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

        Member member2 = Member.builder()
                .realName("공승화")
                .nickName("seunghwa gong")
                .email("hjkwon0814@yahoo.co.kr")
                .password("1234")
                .reviewList(new ArrayList<>())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
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
//
        Menu menu = Menu.builder()
                .Name("restaurant")
                .Image("dog.jpg")
                .Price(1000)
                .build();

        restaurant.addMenu(menu);
        restaurant.addReview(review);
        restaurant.addReview(review1);

        member2.addReview(review);
        member2.addReview(review1);




        Board board = Board.builder()
                .member(member)
                .title("title")
                .contents("hi")
                .isVote(true)
                .type(BoardType.TODAT_EAT_BOARD)
                .build();

        Board board2 = Board.builder()
                .member(member)
                .title("aaa")
                .contents("hi")
                .isVote(true)
                .type(BoardType.TODAT_EAT_BOARD)
                .build();

        Vote vote = Vote.builder()
                .Contents("hi")
                .build();

//        Like like = Like.builder()
//                .member(member)
//                .board(board)
//                .build();

//        Jjim jjim = Jjim.builder()
//                .member(member)
//                .restaurant(restaurant)
//                .build();

        reviewRe.save(review);
        reviewRe.save(review1);
        memberRepository.save(member);
        memberRepository.save(member2);
        boardRepository.save(board);
        boardRepository.save(board2);
        restaurantRepository.save(restaurant);
//        likeRepository.save(like);
//        jjimRepository.save(jjim);

    }
}