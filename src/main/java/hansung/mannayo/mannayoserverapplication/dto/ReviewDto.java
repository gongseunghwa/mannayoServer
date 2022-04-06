package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ReviewDto {

    private String title;

//    private Member member;

    private String content;

    private LocalDateTime writeDate;

    private String image;

    private Float starPoint;

    private Boolean isDeleted;

    private Boolean isModifoed;

}
