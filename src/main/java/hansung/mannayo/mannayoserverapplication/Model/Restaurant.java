package hansung.mannayo.mannayoserverapplication.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Restaurant {

    @Id @GeneratedValue
    private Integer idRestaurant;

    private String Name;

    private Restaurant_Type restaurant_type;

    private String number;

    private String owner;

    private String address;

    private String Address;

    private Integer JJIMcount;

    private LocalDate BusinessStartHours;

    private LocalDate BusinessEndHours;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviewList;

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