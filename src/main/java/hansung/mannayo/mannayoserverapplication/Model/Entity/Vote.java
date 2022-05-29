package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity @Getter
@Setter @NoArgsConstructor
@AllArgsConstructor @Builder
@Table(name = "vote")
public class Vote {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    private String Contents;

    @ColumnDefault("0")
    private Long Count;

    @ManyToOne @JsonManagedReference
    private Board board;

    @OneToMany(mappedBy = "vote" ,cascade = CascadeType.ALL)
    @JsonBackReference
    private List<member_vote> member_votes;


}
