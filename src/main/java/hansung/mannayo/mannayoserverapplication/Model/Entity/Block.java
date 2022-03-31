package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Block {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(targetEntity = Member.class) @JsonManagedReference
    private Member member;

    @NotNull
    @ManyToOne(targetEntity = Member.class) @JsonManagedReference
    private Member target_member;
}
