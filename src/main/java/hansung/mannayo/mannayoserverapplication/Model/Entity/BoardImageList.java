package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
@Table(name = "boardimagelist")
public class BoardImageList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageAddress;

}
