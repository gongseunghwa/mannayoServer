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
    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "blcok_member")
    private Member member;

    @NotNull
    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "blocked_member")
    private Member target_member;
}
