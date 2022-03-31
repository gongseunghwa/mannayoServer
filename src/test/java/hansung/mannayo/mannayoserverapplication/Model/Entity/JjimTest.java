package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
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

    @Test
    public void Test() {
        Jjim jjim = new Jjim();
        Restaurant restaurant = new Restaurant.RestaurantBuilder()
                .Address("123")
                .BusinessDayOff(LocalDate.of(2022,03,31))
                .BusinessEndHours(LocalTime.of(22,10,00))
                .BusinessStartHours(LocalTime.of(10,10,0))
                .Name("Maxicana")
                .number("01051231545")
                .owner("chulsu")
                .restaurant_type(Restaurant_Type.HANSIK)
                .build();

        jjim.setRestaurant(restaurantRepository.getById(2));
        jjim.setMember(memberRepository.getById("hjk"));

        assertEquals("Maxicana", restaurantRepository.getById(2).getName());
    }

}