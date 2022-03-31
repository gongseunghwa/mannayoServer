package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Getter
@Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class Vote {

    @Id @GeneratedValue
    private Integer idVote;

    private String Contents;

    private Integer Count;

    @ManyToOne(targetEntity = Board.class)
    private Board board;

}
