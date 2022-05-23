package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ReviewDto {

    private Long id;

    private String title;

    private Long memberId;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime writeDate;

    private String image;

    private String memberImage;

    private Float starPoint;

    private Boolean isDeleted;

    private Boolean isModifoed;

}
