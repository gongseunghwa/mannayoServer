package hansung.mannayo.mannayoserverapplication.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Menu {

    @Id @GeneratedValue
    private Long idMenu;

    private String Name;

    private Integer Price;

    private String Image;

    @ManyToOne
    private Restaurant restaurant;
}
