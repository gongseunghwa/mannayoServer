package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReviewRepository reviewRepository;


    @Autowired
    EntityManager entityManager;

    @Test
    public void ReviewTest(){
        Member member = Member.builder()
                .nickName("aa")
                .email("tmdhk502@naver.com")
                .password("tmdghk9609!")
                .phoneNumber("010-0202-0303")
                .birth(LocalDate.now())
                .ReportCount(1)
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .loginTypeEnum(LoginType.EMAIL)
                .build();

        Member savedMember = memberRepository.save(member);



        Restaurant restaurant = Restaurant.builder()
                .name("dsd")
                .type(Restaurant_Type.BUNSIK)
                .number("000000010")
                .owner("owner")
                .address("assdsa")
                .jjimcount(10)
                .businessStartHours(LocalTime.now())
                .businessEndHours(LocalTime.now())
                .businessDayOff(LocalDate.now())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        Review review = new Review();
        review.setMember(member);
        review.setRestaurant(restaurant);

        Review savedReview = reviewRepository.save(review);

        assertThat(savedReview.getRestaurant().getId()).isEqualTo(savedRestaurant.getId());
        assertThat(savedReview.getMember().getNickName()).isEqualTo(savedMember.getNickName());



    }

}