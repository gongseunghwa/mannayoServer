package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Service.*;
import hansung.mannayo.mannayoserverapplication.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    String AWSfilepath = "/home/ec2-user/images/";

    String localfilepath = "C://images/restaurant/";

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    JjimService jjimService;

    @Autowired
    MemberService memberService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ResponseService responseService;

    // 메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴
    @ApiOperation(value = "메인화면에서 일식 중식 등.. type을 버튼을 통해 주소로 전달하면 그 type으로 쿼리를 돌려서 list를 리턴")
    @GetMapping("/type")
    ResponseEntity<List<RestaurantListResponse>> findRestaurantbyType(@ApiParam(value = "레스토랑 타입을 입력")@RequestParam Restaurant_Type type, @RequestParam Long memberId){
        Optional<List<Restaurant>> request = restaurantService.findbyRestaurant_type(type);
        Optional<Jjim> jjim;
        Long reviewCount, JjimCount;
        if(request.isPresent()) {
            List<RestaurantListResponse> dto = new ArrayList<>();
            toDto(request.get(),dto);
            for(RestaurantListResponse r : dto) {
                jjim = jjimService.fincByMemberIdAndRestaurantId(memberId, r.getId());
                reviewCount = reviewService.getCountReviewsByRestaurantId(r.getId());
                JjimCount = jjimService.getCountJjimsByRestaurantId(r.getId());
                r.setCountReview(reviewCount);
                r.setCountJjim(JjimCount);
                if(!jjim.isEmpty()) {
                    r.setIsJjim(true);
                } else {
                    r.setIsJjim(false);
                }
            }
            return ResponseEntity.ok().body(dto);
        }

        throw new EntityNotFoundException("Entity not found by given type");
    }

    @ApiOperation(value = "찜 목록 설정된 나의 식당들을 호출한다.")
    @GetMapping("/restaurantJjim")
    ResponseEntity<List<RestaurantListResponse>> findRestaurantJjimByMemberId(@RequestParam Long memberId) {
        Member member = memberService.findbyId(memberId);
        List<Jjim> jjims = member.getJjimList();
        List<RestaurantListResponse> restaurantListResponses = new ArrayList<>();
        List<Restaurant> restaurantList = new ArrayList<>();
        for(Jjim j : jjims) {
            Restaurant restaurant = restaurantService.findbyId(j.getRestaurant().getId()).get();
            restaurantList.add(restaurant);
        }
        toDto(restaurantList, restaurantListResponses);
        for(RestaurantListResponse r : restaurantListResponses) {
            r.setIsJjim(true);
        }

        return ResponseEntity.ok().body(restaurantListResponses);
    }


    //상세정보 페이지
    // fragment를 변경 시켰을 때 다른 query를 돌려서 리뷰리스트 혹은 메뉴를 받을예정
    @ApiOperation(value = "음식점 목록 중 하나를 누르면 게시판 상세정보 페이지로 이동한다")
    @GetMapping("/detail/{id}")
    ResponseEntity<RestaurantDetailResponse> findRestaurantDetail(@ApiParam(value = "레스토랑 id를 입력") @PathVariable Long id){
        RestaurantDetailResponse dto = restaurantService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    //상세정보 페이지
    // fragment를 변경 시켰을 때 다른 query를 돌려서 리뷰리스트 혹은 메뉴를 받을예정
    @ApiOperation(value = "음식점 목록 중 하나를 누르면 게시판 상세정보 페이지로 이동한다")
    @GetMapping("/detailmap/{name}")
    ResponseEntity<RestaurantDetailResponse> findRestaurantDetailByMap(@ApiParam(value = "레스토랑 이름을 입력") @PathVariable String name){
        RestaurantDetailResponse dto = restaurantService.findByName(name);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "음식점이 존재하면 return")
    @GetMapping("/summarymap/{name}")
    ResponseEntity<RestaurantMapDto> findRestaurantMapDto(@ApiParam(value = "레스토랑 이름을 입력") @PathVariable String name) {
        RestaurantMapDto restaurantMapDto = restaurantService.findsummaryByName(name);
        return ResponseEntity.ok().body(restaurantMapDto);
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

    @ApiOperation(value = "restaurant data 입력")
    @PostMapping(value = "/input")
    public ResponseEntity<CommonResult> setRestaurantImage(@RequestParam String Address, @RequestParam Restaurant_Type restaurant_type
    , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endHours, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime startHours, @RequestParam String name, @RequestParam String number
    , @RequestParam String owner, @RequestPart MultipartFile multipartFile) {
        Date date = new Date(); // 파일명이 겹치는것을 방지 하기 위해 파일명에 시간변수를 추가
        StringBuilder sb = new StringBuilder(); // 파일명 스트링 빌더
        CommonResult commonResult = new CommonResult();

        Restaurant restaurant = Restaurant.builder()
                .businessStartHours(startHours)
                .businessEndHours(endHours)
                .owner(owner)
                .type(restaurant_type)
                .name(name)
                .number(number)
                .address(Address)
                .build();

        Long restId = restaurantService.insert(restaurant);

        if(multipartFile.isEmpty()) { // request된 파일의 존재여부 확인
            sb.append("none");
            commonResult = responseService.getFailResult();
        } else {
            sb.append(date.getTime());
            sb.append(multipartFile.getOriginalFilename());
        }

        if(!multipartFile.isEmpty()) { // request된 파일이 존재한다면

            File dest = new File(AWSfilepath + sb.toString()); // 파일 생성
            try {
                restaurant = restaurantService.findbyId(restId).get(); // id로 Entity 찾아옴
                if(restaurant.getImageAddress() == null) { // 이미 이미지 주소가 없다면 (기존에 프로필을 올린적이 없다면)
                    restaurant.setImageAddress(AWSfilepath + sb.toString()); // member Entity에 이미지주소 저장
                    restaurantService.updateImageAddress(restaurant); // 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                }else {
                    File file = new File(restaurant.getImageAddress()); // 기존에 저장된 파일 경로 DB에서 가져온 후 파일 인스턴스 생성
                    if(file.exists()) { // file이 존재한다면
                        file.delete(); // 삭제
                    }

                    restaurant.setImageAddress(AWSfilepath + sb.toString()); // 새로운 이미지 주소 DB에 저장
                    restaurantService.updateImageAddress(restaurant); // Entity 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        commonResult = responseService.getSuccessResult();
        return ResponseEntity.ok().body(commonResult);
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
