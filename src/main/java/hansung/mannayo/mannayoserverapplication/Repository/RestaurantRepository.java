package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
