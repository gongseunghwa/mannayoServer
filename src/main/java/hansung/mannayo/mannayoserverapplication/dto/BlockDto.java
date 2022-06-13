package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class BlockDto { // 차단 내역 insert를 위한 DTO

    private Long memberId;

    private Long blockedMemberId;

}
