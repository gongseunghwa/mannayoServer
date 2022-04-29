package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class BlockDto {

    private Long memberId;

    private Long blockedMemberId;

}
