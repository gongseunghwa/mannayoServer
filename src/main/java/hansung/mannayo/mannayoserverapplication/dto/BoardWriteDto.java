package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;

import java.time.LocalDateTime;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class BoardWriteDto {

    private String nickName;

    private String title;

    private String contents;

    private String image;

    private BoardType boardType;
}
