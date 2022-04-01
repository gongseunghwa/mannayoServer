package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter
@Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class Vote {

    @Id @GeneratedValue
    private Integer idVote;

    @NotNull
    private String Contents;

    @ColumnDefault("0")
    private Integer Count;

    private Board board;

}
