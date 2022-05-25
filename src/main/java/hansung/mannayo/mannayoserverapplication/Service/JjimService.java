package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface JjimService {

    Optional<List<Jjim>> findByMemberId(Long id);

    Optional<Jjim> fincByMemberIdAndRestaurantId(Long memberid, Long restaurantid);

    Jjim insertJjim(Member member, Restaurant restaurant);

    boolean deleteJjim(Long membeerid, Long Restaurantid);

    Long getCountJjimsByRestaurantId(Long id);
}
