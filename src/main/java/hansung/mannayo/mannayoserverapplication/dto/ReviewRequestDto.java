package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReviewRequestDto {

    private Long memberId;

    private Long restaurantId;

    private String content;

    private Float starPoint;

}
