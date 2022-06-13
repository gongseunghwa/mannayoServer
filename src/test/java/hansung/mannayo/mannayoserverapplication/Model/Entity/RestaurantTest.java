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
class RestaurantTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insert() {
        Restaurant restaurant = Restaurant.builder()
                .name("SH restaurant")
                .type(Restaurant_Type.SULJIP)
                .number("010-1323-3332")
                .owner("seunghwa gong")
                .address("seoul gwangjin")
                .businessStartHours(LocalTime.of(9,30))
                .businessEndHours(LocalTime.of(11,0))
                .build();

        restaurantRepository.save(restaurant);
    }

}