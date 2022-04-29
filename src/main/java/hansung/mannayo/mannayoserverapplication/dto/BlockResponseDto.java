package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class BlockResponseDto {

    private Long memberId;

    private String nickname;

}
