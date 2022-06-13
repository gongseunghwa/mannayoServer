package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Repository.JjimRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JjimServiceImpl implements JjimService{

    @Autowired
    JjimRepository jjimRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Jjim insertJjim(Member member, Restaurant restaurant) {
        Jjim jjim = Jjim.builder()
                .member(member)
                .restaurant(restaurant)
                .build();
        return jjimRepository.save(jjim);
    }

    @Override
    public Optional<List<Jjim>> findByMemberId(Long id) {
        return jjimRepository.findByMember_Id(id);
    }

    @Override
    public Optional<Jjim> fincByMemberIdAndRestaurantId(Long memberid, Long restaurantid) {
        return jjimRepository.findByMemberIdAndRestaurantId(memberid, restaurantid);
    }

    @Override
    public Long getCountJjimsByRestaurantId(Long id) {
        return jjimRepository.countJjimsByRestaurantId(id);
    }

    @Override
    public boolean deleteJjim(Long memberid, Long restaurantid) {
        Jjim jjim = new Jjim();
        jjim = jjimRepository.findByMemberIdAndRestaurantId(memberid, restaurantid).get();
        jjimRepository.delete(jjim);

        if(jjim != null) {
            return true;
        }
        return false;
    }
}
