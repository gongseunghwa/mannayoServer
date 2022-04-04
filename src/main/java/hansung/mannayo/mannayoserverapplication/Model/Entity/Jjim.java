package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jjim {

    @Id @GeneratedValue
    private Long JJIM_ID;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Member member;
}
