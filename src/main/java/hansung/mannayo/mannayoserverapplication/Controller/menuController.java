package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Menu;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Repository.MenuRepository;
import hansung.mannayo.mannayoserverapplication.Service.MenuService;
import hansung.mannayo.mannayoserverapplication.dto.MenuResponse;
import hansung.mannayo.mannayoserverapplication.exceptions.NotFoundImageException;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class menuController {

    @Autowired
    MenuService menuService;

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

}
