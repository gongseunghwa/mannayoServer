package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantDetailResponse;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Optional<List<Restaurant>> findbyRestaurant_type(Restaurant_Type type);

    RestaurantDetailResponse findById(Long id);

    Optional<Restaurant> findbyId(Long id);

    default RestaurantDetailResponse EntitytoDto(Restaurant restaurant) {
        RestaurantDetailResponse entity = RestaurantDetailResponse.builder()
                .address(restaurant.getAddress())
                .name(restaurant.getName())
                .end_time(restaurant.getBusinessEndHours())
                .start_time(restaurant.getBusinessStartHours())
                .number(restaurant.getNumber())
                .jjimcount(restaurant.getJjimcount())
                .owner(restaurant.getOwner())
                .type(restaurant.getType())
                .build();
        return entity;
    }
}
