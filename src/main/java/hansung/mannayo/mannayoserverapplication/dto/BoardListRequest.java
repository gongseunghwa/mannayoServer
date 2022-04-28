package hansung.mannayo.mannayoserverapplication.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class BoardListRequest {

    public String title;

    public Long id;

    public String writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    public LocalDateTime writeDate;
}
