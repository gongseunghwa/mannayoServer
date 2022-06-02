package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Optional<List<Restaurant>> findbyRestaurant_type(Restaurant_Type type) {
        return restaurantRepository.findByType(type);
    }

    @Override
    public Optional<Restaurant> findbyId(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public RestaurantDetailResponse findById(Long id) {
        Optional<Restaurant> restaurant= restaurantRepository.findById(id);
        if(restaurant.isPresent()) {
            RestaurantDetailResponse response;
            response = EntitytoDto(restaurant.get());
            return response;
        }
        throw new EntityNotFoundException("Cannot found anything by given id");
    }

    @Override
    public RestaurantDetailResponse findByName(String name) {
        Optional<Restaurant> restaurant= restaurantRepository.findByName(name);
        if(restaurant.isPresent()) {
            RestaurantDetailResponse response;
            response = EntitytoDto(restaurant.get());
            return response;
        }
        throw new EntityNotFoundException("Cannot found anything by given id");
    }

    @Override
    public Long insert(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        return restaurant1.getId();
    }

    @Override
    public void updateImageAddress(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}

