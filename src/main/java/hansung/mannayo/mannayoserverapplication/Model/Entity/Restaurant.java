package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import hansung.mannayo.mannayoserverapplication.Model.Type.Restaurant_Type;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "restaurant")
public class Restaurant {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Restaurant_Type type;

    @NotNull
    private String number;

    @NotNull
    private String owner;

    @NotNull
    private String address;

    @ColumnDefault("0")
    private Integer jjimcount;

    private String imageAddress;

    @NotNull
    private LocalTime businessStartHours;

    @NotNull
    private LocalTime businessEndHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Review> reviewList;

//    private LocalDate businessDayOff;

    @ColumnDefault("0")
    private Integer reviewCount;

    @ColumnDefault("0")
    private Float starPointInfo;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Menu> menuList;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Jjim> jjimList;

    public void addJjim(Jjim jjim){
        this.jjimList.add(jjim);
        jjim.setRestaurant(this);
    }

    public void addReview(Review review){
        this.reviewList.add(review);
        review.setRestaurant(this);
    }
    public void addMenu(Menu menu){
        this.menuList.add(menu);
        menu.setRestaurant(this);
    }

    @PrePersist
    public void save(){
        this.jjimcount = 0;
        this.reviewCount = 0;
    }


}
//CREATE TABLE Restaurant (
//  idRestaurant INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
//  Name VARCHAR(30)  NOT NULL  ,
//  Type_2 ENUM  NOT NULL  ,
//  Number VARCHAR(20)  NOT NULL  ,
//  Owner VARCHAR(10)  NOT NULL  ,
//  Address VARCHAR(100)  NOT NULL  ,
//  JjimCount INTEGER UNSIGNED  NULL DEFAULT 0 ,
//  BusinessStartHours DATE  NOT NULL  ,
//  StarPointInfo INTEGER UNSIGNED  NOT NULL DEFAULT 0 ,
//  ReviewCount INTEGER UNSIGNED  NOT NULL DEFAULT 0 ,
//  BusinessEndHours DATE  NOT NULL  ,
//  BusinessDayOff VARCHAR(100)  NULL    ,
//PRIMARY KEY(idRestaurant));