package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ReviewRequestDto {

    private Long memberId;

    private Long restaurantId;

    private String content;

    private Float starPoint;

}
