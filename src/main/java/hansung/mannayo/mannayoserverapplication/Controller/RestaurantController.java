package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Service.RestaurantService;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantListResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    // 메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴
    @ApiOperation(value = "메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴")
    @GetMapping("/type/{type}")
    ResponseEntity<List<RestaurantListResponse>> findRestaurantbyType(@ApiParam(value = "레스토랑 타입을 입력")@PathVariable Restaurant_Type type){
        Optional<List<Restaurant>> request = service.findbyRestaurant_type(type);
        if(request.isPresent()) {
            List<RestaurantListResponse> dto = new ArrayList<>();
            toDto(request.get(),dto);
            return ResponseEntity.ok().body(dto);
        }

        throw new EntityNotFoundException("Entity not found by given type");
    }


    //기본 정보를 들어갔을 때 review랑 상세메뉴 중 상세메뉴만 먼저 보이게 한다
    // fragment를 변경 시켰을 때 다른 query를 돌려서 리뷰리스트를 받을예정
    @ApiOperation(value = "게시판 목록 중 하나를 누르면 게시판 상세정보 페이지로 이동한다")
    @GetMapping("/detail/{id}")
    ResponseEntity<Restaurant> findRestaurantDetail(@ApiParam(value = "레스토랑 id를 입력") @PathVariable Long id){
        Optional<Restaurant> dto = service.findById(id);
        if(dto.isPresent()) {
            return ResponseEntity.ok().body(dto.get());
        }
        throw new EntityNotFoundException("Entity not found by given id");
    }

    public void toDto(List<Restaurant> restaurant, List<RestaurantListResponse> dto){
        for(int i=0;i<restaurant.size();i++){
            RestaurantListResponse request = RestaurantListResponse.builder()
                    .id(restaurant.get(i).getId())
                    .address(restaurant.get(i).getAddress())
                    .name(restaurant.get(i).getName())
                    .type(restaurant.get(i).getType())
                    .build();
            dto.add(request);
        }
    }
}
