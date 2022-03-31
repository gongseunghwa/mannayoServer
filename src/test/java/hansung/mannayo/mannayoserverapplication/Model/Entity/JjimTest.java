package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        jjim.setRestaurant(restaurantRepository.getById(3));
        jjim.setMember(memberRepository.getById("hjk"));

        assertEquals("Maxicana", restaurantRepository.getById(3).getName());
    }

}