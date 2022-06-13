package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantDetailResponse;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantMapDto;
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
    public RestaurantMapDto findsummaryByName(String name) {
        Optional<Restaurant> restaurant= restaurantRepository.findByName(name);
        if(restaurant.isPresent()) {
            RestaurantMapDto response = RestaurantMapDto.builder()
                    .address(restaurant.get().getAddress())
                    .open(restaurant.get().getBusinessStartHours())
                    .close(restaurant.get().getBusinessEndHours())
                    .isExist(true)
                    .name(restaurant.get().getName())
                    .id(restaurant.get().getId())
                    .build();
            return response;
        }else {
            RestaurantMapDto response = RestaurantMapDto.builder()
                    .address("등록정보 없음.")
                    .open(null)
                    .close(null)
                    .isExist(false)
                    .name("등록정보 없음.")
                    .id(null)
                    .build();
            return response;
        }
    }

    @Override
    public void updateImageAddress(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}

