package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class LikeDto {

    private Long member_id;

    private Long board_id;

}
