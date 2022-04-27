package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data @Setter @Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class BoardDto {

    private Integer memberId;

    private String nickName;

    private String title;

    private String contents;

    private LocalDateTime date;

    private String image;

    private BoardType boardType;
}
