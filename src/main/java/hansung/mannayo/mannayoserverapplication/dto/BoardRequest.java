package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private Long memberId;

    private String title;

    private String contents;

    private Boolean isVote;

    private BoardType type;
}
