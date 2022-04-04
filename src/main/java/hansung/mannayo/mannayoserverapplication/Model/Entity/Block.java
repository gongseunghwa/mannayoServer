package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "block")
public class Block {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    @NotNull
    @ManyToOne @JsonManagedReference
    private Member member;

    @NotNull
    @ManyToOne @JsonManagedReference
    private Member target_member;
}
