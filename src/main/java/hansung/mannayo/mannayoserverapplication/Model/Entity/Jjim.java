package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Entity @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jjim")
public class Jjim {

    @Id @GeneratedValue
    @Column(name = "jjim_id")
    private Long JJIM_ID;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
