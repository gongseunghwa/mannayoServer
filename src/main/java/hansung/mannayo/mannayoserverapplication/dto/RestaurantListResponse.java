package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import lombok.*;

import java.time.LocalTime;

/*
 *  가게의 List를 받아올 때 사용 할 Dto
 */
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RestaurantListResponse {

    Long id; //가게의 아이디

    String name; //가게의 이름

    Restaurant_Type type; // 가게의 타입(한식 중식 양식 일식..)

    String address; // 가게의 주소

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime starttime; // 시작시간

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime endtime; // 끝나는 시간

    float point; // 평점

    String imageAddress;

}
