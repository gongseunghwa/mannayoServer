package hansung.mannayo.mannayoserverapplication.Model.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Like {

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne @JsonManagedReference
    private Member member;

    @ManyToOne @JsonManagedReference
    private Board board;
}
