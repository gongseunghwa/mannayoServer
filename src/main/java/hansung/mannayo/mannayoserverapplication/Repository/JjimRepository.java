package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JjimRepository extends JpaRepository<Jjim, Long> {

    Optional<List<Jjim>> findByMember_Id(Long id);

    Optional<Jjim> findByMemberIdAndRestaurantId(Long memberId, Long restaurantId);

    Long countJjimsByRestaurantId(Long id);
}
