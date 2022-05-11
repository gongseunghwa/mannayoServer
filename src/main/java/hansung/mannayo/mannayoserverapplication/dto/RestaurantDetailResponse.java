package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor
@Setter
@Getter
@Builder
public class RestaurantDetailResponse {

    private String name;

    private Restaurant_Type type;

    private String number;

    private String address;

    private String owner;

    private Integer jjimcount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime start_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime end_time;

}
