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
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .address("서울특별시 송파구 송파대로34길 35")
                .name("맥시카나")
                .type(Restaurant_Type.HANSIK)
                .number("0234331857")
                .owner("김철수")
                .menuList(new ArrayList<Menu>())
                .businessStartHours(LocalTime.of(10,00))
                .businessEndHours(LocalTime.of(22,00))
                .businessDayOff(LocalDate.of(2022,03,31))
                .reviewList(new ArrayList<>())
                .imageAddress("")
                .starPointInfo(5.0f)
                .build();

        Restaurant restaurant2 = Restaurant.builder()
                .address("서울특별시 서초구 동광로11길 25")
                .name("페리카나")
                .type(Restaurant_Type.HANSIK)
                .number("025227878")
                .owner("이호성")
                .menuList(new ArrayList<Menu>())
                .businessStartHours(LocalTime.of(10,00))
                .businessEndHours(LocalTime.of(22,00))
                .businessDayOff(LocalDate.of(2022,03,31))
                .reviewList(new ArrayList<>())
                .imageAddress("")
                .starPointInfo(5.0f)
                .build();

        Restaurant restaurant3 = Restaurant.builder()
                .address("경기도 오산시 동부대로 436번길 55-18")
                .name("교촌")
                .type(Restaurant_Type.HANSIK)
                .number("0313713501")
                .owner("여맹구")
                .menuList(new ArrayList<Menu>())
                .businessStartHours(LocalTime.of(10,00))
                .businessEndHours(LocalTime.of(22,00))
                .businessDayOff(LocalDate.of(2022,03,31))
                .reviewList(new ArrayList<>())
                .imageAddress("")
                .starPointInfo(5.0f)
                .build();
        Menu menu1 = Menu.builder()
                .Name("어향요리치킨")
                .Price(19800)
                .Image("C://images/menu/어향요리치킨.jpg")
                .build();

        Menu menu2 = Menu.builder()
                .Name("깐풍요리치킨")
                .Price(29800)
                .Image("C://images/menu/깐풍요리치킨.jpg")
                .build();
        ArrayList<Menu> maxicana_menu = new ArrayList<Menu>();
        maxicana_menu.add(menu1);
        maxicana_menu.add(menu2);
        restaurant.setMenuList(maxicana_menu);

        Member member = Member.builder()
                .realName("맛나요2")
                .nickName("null")
                .email("mannayo2@hansung.ac.kr")
                .password(passwordEncoder.encode("1234"))
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

        Member member2 = Member.builder()
                .realName("맛나요")
                .nickName("null")
                .email("mannayo@hansung.ac.kr")
                .password(passwordEncoder.encode("1234"))
                .reviewList(new ArrayList<>())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

        Member member3 = Member.builder()
                .realName("맛나요3")
                .nickName("null")
                .email("mannayo3@hansung.ac.kr")
                .password(passwordEncoder.encode("1234"))
                .reviewList(new ArrayList<>())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

        Member member4 = Member.builder()
                .realName("맛나요4")
                .nickName("null")
                .email("mannayo4@hansung.ac.kr")
                .password(passwordEncoder.encode("1234"))
                .reviewList(new ArrayList<>())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .phoneNumber("01012341234")
                .loginTypeEnum(LoginType.EMAIL)
                .birth(LocalDate.now())
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .build();

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

        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        boardRepository.save(board);
        boardRepository.save(board2);
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);
//        likeRepository.save(like);
//        jjimRepository.save(jjim);

    }
}