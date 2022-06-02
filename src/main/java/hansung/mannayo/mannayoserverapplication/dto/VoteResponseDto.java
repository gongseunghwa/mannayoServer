package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Data @Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class VoteResponseDto {
    private String contents;

    private Long Count;

    private Boolean amIVote;

    private Long id;
}
