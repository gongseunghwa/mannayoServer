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
    @Column(name = "like_id")
    private Long id;

    @JoinColumn(name = "member_nickname")
    @ManyToOne @JsonManagedReference
    private Member member;

    @JoinColumn(name = "board_id")
    @ManyToOne @JsonManagedReference
    private Board board;
}
