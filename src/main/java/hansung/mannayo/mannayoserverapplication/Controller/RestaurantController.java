package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Service.RestaurantService;
import hansung.mannayo.mannayoserverapplication.dto.ImageDto;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantDetailResponse;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantListResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    // 메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴
    @ApiOperation(value = "메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴")
    @GetMapping("/type")
    ResponseEntity<List<RestaurantListResponse>> findRestaurantbyType(@ApiParam(value = "레스토랑 타입을 입력")@RequestParam Restaurant_Type type){
        Optional<List<Restaurant>> request = restaurantService.findbyRestaurant_type(type);
        if(request.isPresent()) {
            List<RestaurantListResponse> dto = new ArrayList<>();
            toDto(request.get(),dto);
            return ResponseEntity.ok().body(dto);
        }

        throw new EntityNotFoundException("Entity not found by given type");
    }


    //상세정보 페이지
    // fragment를 변경 시켰을 때 다른 query를 돌려서 리뷰리스트 혹은 메뉴를 받을예정
    @ApiOperation(value = "음식점 목록 중 하나를 누르면 게시판 상세정보 페이지로 이동한다")
    @GetMapping("/detail/{id}")
    ResponseEntity<RestaurantDetailResponse> findRestaurantDetail(@ApiParam(value = "레스토랑 id를 입력") @PathVariable Long id){
        RestaurantDetailResponse dto = restaurantService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ImageDto> getProfileImage(@RequestParam("id") Long id) throws IOException {
        Optional<Restaurant> restaurant = restaurantService.findbyId(id);
        ImageDto imageDto = new ImageDto();
        if(restaurant.isPresent()) {
            String imagename = restaurant.get().getImageAddress();
            InputStream imageStream = new FileInputStream(imagename);
            imageDto.setImage(IOUtils.toByteArray(imageStream));
            imageStream.close();
            return ResponseEntity.ok().body(imageDto);
        }
        throw new EntityNotFoundException("Entity not found by given type");

    }

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "/profileimage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImage2(@RequestParam("id") Long id) throws IOException {
        Optional<Restaurant> restaurant = restaurantService.findbyId(id);
        ImageDto imageDto = new ImageDto();
        if(restaurant.isPresent()) {
            String imagename = restaurant.get().getImageAddress();
            InputStream imageStream = new FileInputStream(imagename);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            return ResponseEntity.ok().body(imageByteArray);
        }
        throw new EntityNotFoundException("Entity not found by given type");
    }

    public void toDto(List<Restaurant> restaurant, List<RestaurantListResponse> dto){
        for(int i=0;i<restaurant.size();i++){
            RestaurantListResponse request = RestaurantListResponse.builder()
                    .id(restaurant.get(i).getId())
                    .address(restaurant.get(i).getAddress())
                    .name(restaurant.get(i).getName())
                    .type(restaurant.get(i).getType())
                    .starttime(restaurant.get(i).getBusinessStartHours())
                    .endtime(restaurant.get(i).getBusinessEndHours())
                    .point(restaurant.get(i).getStarPointInfo())
                    .imageAddress(restaurant.get(i).getImageAddress())
                    .build();
            dto.add(request);
        }
    }


}
