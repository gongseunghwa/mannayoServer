package hansung.mannayo.mannayoserverapplication.dto;

import lombok.*;

@Getter
@Setter
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class MenuResponse {

    private Long id;

    private String name;

    private Integer price;

    private boolean isbest;

    private String image;

}
