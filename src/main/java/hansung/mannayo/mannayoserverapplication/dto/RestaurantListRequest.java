package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import lombok.*;
/*
 *  가게의 List를 받아올 때 사용 할 Dto
 */
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RestaurantListRequest {

    Long id; //가게의 아이디

    String name; //가게의 이름

    Restaurant_Type type; // 가게의 타입(한식 중식 양식 일식..)

    String address; // 가게의 주소

}
