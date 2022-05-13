package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Menu;
import hansung.mannayo.mannayoserverapplication.Repository.MenuRepository;
import hansung.mannayo.mannayoserverapplication.dto.MenuResponse;
import hansung.mannayo.mannayoserverapplication.exceptions.NotFoundImageException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class menuController {

    private final MenuRepository menuRepository;

    @GetMapping("/{id}")
    ResponseEntity<MenuResponse> findMenuById(Long id){
        Optional<Menu> menu = menuRepository.findByRestaurantId(id);
        if(menu.isPresent()){
            try {
                String path = "실제 이미지 위치";
                FileSystemResource resource = new FileSystemResource(path + menu.get().getImage());
                if(!resource.exists()){
                    throw new NotFoundImageException("Cannot found image");
                }
                HttpHeaders header = new HttpHeaders();
                Path filePath = null;
                filePath = Paths.get(path + menu.get().getImage());
                header.add("Content-Type", Files.probeContentType(filePath));
                MenuResponse menuResponse = MenuResponse.builder()
                        .name(menu.get().getName())
                        .resource(resource)
                        .price(menu.get().getPrice())
                        .build();
                return new ResponseEntity<MenuResponse>(menuResponse,header, HttpStatus.OK);
            }catch(Exception e){
                throw new NotFoundImageException("Cannot found image");
            }
        }
        throw new EntityNotFoundException("Cannot find by given id");
    }

}
