package hansung.mannayo.mannayoserverapplication.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

@Getter
@Setter @Builder
public class MenuResponse {

    private String name;

    //이미지 출력을 위한 파일시스템리소스
    private FileSystemResource resource;

    private Integer price;
}
