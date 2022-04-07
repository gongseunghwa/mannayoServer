package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "blocks")

public class Block {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST) @JsonManagedReference
    @JoinColumn(name = "block_member")
    private Member member;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST) @JsonManagedReference
    @JoinColumn(name = "blocked_member")
    private Member target_member;
}
