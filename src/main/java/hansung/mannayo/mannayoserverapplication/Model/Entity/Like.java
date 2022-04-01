package hansung.mannayo.mannayoserverapplication.Model.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity @Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(targetEntity = Member.class) @JsonManagedReference
    private Member member;

    @ManyToOne(targetEntity = Board.class) @JsonManagedReference
    private Board board;
}
