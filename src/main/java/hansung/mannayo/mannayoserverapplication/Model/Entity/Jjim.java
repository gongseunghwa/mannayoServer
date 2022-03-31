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
    private Integer JJIM_ID;

    @ManyToOne(targetEntity = Restaurant.class)
    private Restaurant restaurant;

    @ManyToOne(targetEntity = Member.class)
    private Member member;
}
