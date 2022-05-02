package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Optional<List<Restaurant>> findbyRestaurant_type(Restaurant_Type type);

    Optional<Restaurant> findById(Long id);
}
