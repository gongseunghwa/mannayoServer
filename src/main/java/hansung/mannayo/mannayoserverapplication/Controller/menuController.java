package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Menu;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import hansung.mannayo.mannayoserverapplication.Repository.MenuRepository;
import hansung.mannayo.mannayoserverapplication.Service.MenuService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.Service.RestaurantService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.MenuResponse;
import hansung.mannayo.mannayoserverapplication.exceptions.NotFoundImageException;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class menuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ResponseService responseService;

    String AWSfilepath = "/home/ec2-user/images/";

    String localfilepath = "C://images/review/";

    @GetMapping("/{id}")
    public ResponseEntity<List<MenuResponse>> findMenuByRestaurantId(@PathVariable Long id){
        Optional<List<Menu>> menuList = menuService.findMenuByRestaurantId(id);
        List<MenuResponse> menuResponses = new ArrayList<>();
        for(Menu menu : menuList.get()) {
            MenuResponse menuResponse = MenuResponse.builder()
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .id(menu.getIdMenu())
                    .isbest(menu.isBest())
                    .image(menu.getImage())
                    .build();
            menuResponses.add(menuResponse);
        }

        return ResponseEntity.ok().body(menuResponses);
    }

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getReviewImage(@PathVariable("id") Long id) throws IOException {
        Optional<Menu> menu = menuService.findById(id);
        String imagename = menu.get().getImage();
        InputStream imageStream = new FileInputStream(imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

//    @ApiOperation(value = "menu data 입력")
//    @PostMapping(value = "/input")
//    public ResponseEntity<CommonResult> setMenuImage(@RequestParam String name, @RequestParam Integer price, @RequestParam Boolean isBest,
//                                                     @RequestParam Long restaurantId,@RequestPart MultipartFile multipartFile) {
//        Date date = new Date(); // 파일명이 겹치는것을 방지 하기 위해 파일명에 시간변수를 추가
//        StringBuilder sb = new StringBuilder(); // 파일명 스트링 빌더
//        CommonResult commonResult = new CommonResult();
//
//        Menu menu = Menu.builder()
//                .Name(name)
//                .Price(price)
//                .restaurant(restaurantService.findbyId(restaurantId).get())
//                .isBest(isBest)
//                .build();
//
//        Long menuId = menuService.insert(menu);
//
//        if(multipartFile.isEmpty()) { // request된 파일의 존재여부 확인
//            sb.append("none");
//            commonResult = responseService.getFailResult();
//        } else {
//            sb.append(date.getTime());
//            sb.append(multipartFile.getOriginalFilename());
//        }
//
//        if(!multipartFile.isEmpty()) { // request된 파일이 존재한다면
//
//            File dest = new File(AWSfilepath + sb.toString()); // 파일 생성
//            try {
//                menu = menuService.findbyId(menuId).get(); // id로 Entity 찾아옴
//                if(restaurant.getImageAddress() == null) { // 이미 이미지 주소가 없다면 (기존에 프로필을 올린적이 없다면)
//                    restaurant.setImageAddress(AWSfilepath + sb.toString()); // member Entity에 이미지주소 저장
//                    restaurantService.updateImageAddress(restaurant); // 업데이트
//                    multipartFile.transferTo(dest); // 파일 저장
//                }else {
//                    File file = new File(restaurant.getImageAddress()); // 기존에 저장된 파일 경로 DB에서 가져온 후 파일 인스턴스 생성
//                    if(file.exists()) { // file이 존재한다면
//                        file.delete(); // 삭제
//                    }
//
//                    restaurant.setImageAddress(AWSfilepath + sb.toString()); // 새로운 이미지 주소 DB에 저장
//                    restaurantService.updateImageAddress(restaurant); // Entity 업데이트
//                    multipartFile.transferTo(dest); // 파일 저장
//                }
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        commonResult = responseService.getSuccessResult();
//        return ResponseEntity.ok().body(commonResult);
//    }

}
