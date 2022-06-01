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

    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

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


        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

    }
}