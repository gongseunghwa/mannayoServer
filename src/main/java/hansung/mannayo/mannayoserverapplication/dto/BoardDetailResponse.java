package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;

import java.time.LocalDateTime;

@Data @Setter @Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class BoardDetailResponse {

    private Long memberId;

    private Long boardId;

    private String nickName;

    private String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    private String image;

    private BoardType boardType;

    private Long likeCount;

    private Long commentCount;

    private Boolean isVote;

    private Boolean isProfile;
}
