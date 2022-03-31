package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.JjimRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JjimTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JjimRepository jjimRepository;
    @Test
    public void Test() {
        Jjim jjim = new Jjim();
        Restaurant restaurant = new Restaurant.RestaurantBuilder()
                .idRestaurant(1)
                .Address("123")
                .BusinessDayOff(LocalDate.of(2022,03,31))
                .BusinessEndHours(LocalTime.of(22,10,00))
                .BusinessStartHours(LocalTime.of(10,10,0))
                .Name("Maxicana")
                .number("01051231545")
                .owner("chulsu")
                .restaurant_type(Restaurant_Type.HANSIK)
                .build();

        Member member = new Member.MemberBuilder()
                .NickName("abc")
                .Email("hjkwon0814@navercom")
                .Password("1234")
                .accountTypeEnum(AccountType.ADMISTRATOR)
                .PhoneNumber("010-1234-1234")
                .loginTypeEnum(LoginType.EMAIL)
                .build();

        jjim.setRestaurant(restaurantRepository.getById(0));
        jjim.setMember(memberRepository.getById("hjk"));
        jjim.setJJIM_ID(0);

        assertEquals(jjimRepository.getById(1).getRestaurant().getName(), restaurantRepository.getById(0).getName());
    }

}