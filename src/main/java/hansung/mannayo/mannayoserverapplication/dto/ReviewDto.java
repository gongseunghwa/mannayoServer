package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import lombok.*;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ReviewDto {

    private Long id;

    private Long memberId;

    private String memberNickname;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime writeDate;

    private String image;

    private String memberImage;

    private Float starPoint;

    private Boolean isDeleted;

    private Boolean isModifoed;

}
