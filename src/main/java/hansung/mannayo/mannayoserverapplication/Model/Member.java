package hansung.mannayo.mannayoserverapplication.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Member {

    @Id
    @Column(nullable = false, unique = true)
    private String NickName;

    @NotNull
    private String Email;

    @NotNull
    @JsonIgnore
    private String Password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountTypeEnum;

    @NotNull
    private String PhoneNumber;

    @NotNull
    private LocalDate Birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginType loginTypeEnum;

    private String ImageAddress;

    @NotNull
    private Integer ReportCount;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviewList;

    private String bb;

}

//CREATE TABLE Member (
//  NICKNAME VARCHAR(20)  NOT NULL   AUTO_INCREMENT,
//  Email VARCHAR(255)  NOT NULL  ,
//  Password_2 VARCHAR(255)  NOT NULL  ,
//  AccountType ENUM  NOT NULL  ,
//  PhoneNumber VARCHAR(20)  NOT NULL  ,
//  Birth DATE  NOT NULL  ,
//  LoginType ENUM  NOT NULL  ,
//  Image VARCHAR(100)  NULL  ,
//  ReportCount INTEGER UNSIGNED  NOT NULL DEFAULT 0   ,
//PRIMARY KEY(NICKNAME));