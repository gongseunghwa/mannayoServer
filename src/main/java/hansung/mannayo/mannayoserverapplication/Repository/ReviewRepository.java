package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<Review> findByRestaurantId(Long id);
}
