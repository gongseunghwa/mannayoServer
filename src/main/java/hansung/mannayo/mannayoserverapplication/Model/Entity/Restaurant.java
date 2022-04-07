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
    private Long idRestaurant;

    @NotNull
    private String Name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Restaurant_Type restaurant_type;

    @NotNull
    private String number;

    @NotNull
    private String owner;

    @NotNull
    private String Address;

    @ColumnDefault("0")
    private Integer JJIMcount;

    @NotNull
    private LocalTime BusinessStartHours;

    @NotNull
    private LocalTime BusinessEndHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Review> reviewList;

    @NotNull
    private LocalDate BusinessDayOff;

    @ColumnDefault("0")
    private Integer reviewCount;

    @ColumnDefault("0")
    private Integer StarPointInfo;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Jjim> jjimList = new ArrayList<>();

    public void addJjim(Jjim jjim){
        this.jjimList.add(jjim);
        jjim.setRestaurant(this);
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