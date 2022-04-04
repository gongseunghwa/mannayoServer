package hansung.mannayo.mannayoserverapplication.Model.Entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity @Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Menu {

    @Id @GeneratedValue
    private Long idMenu;

    private String Name;

    private Integer Price;

    private String Image;

    @ManyToOne
    private Restaurant restaurant;
}
