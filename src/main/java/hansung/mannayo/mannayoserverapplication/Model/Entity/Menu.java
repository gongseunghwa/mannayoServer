package hansung.mannayo.mannayoserverapplication.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Menu {

    @Id @GeneratedValue
    private Long idMenu;

    @NotNull
    private String Name;

    @NotNull
    private Integer Price;

    private String Image;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Restaurant restaurant;
}
