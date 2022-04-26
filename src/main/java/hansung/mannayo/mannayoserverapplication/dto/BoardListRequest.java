package hansung.mannayo.mannayoserverapplication.dto;


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

    public LocalDateTime writeDate;
}
