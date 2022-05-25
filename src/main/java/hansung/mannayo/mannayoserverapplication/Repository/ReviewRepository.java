package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<List<Review>> findByRestaurantId(Long id);

    Long countReviewsByRestaurantId(Long id);

    @Query("select count(r) from Review r")
    Long countReview();
}
