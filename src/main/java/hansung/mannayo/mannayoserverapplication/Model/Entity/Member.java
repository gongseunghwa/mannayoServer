package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "member")
public class Member implements Serializable {

    @Id @GeneratedValue
    private Long id;

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

    @ColumnDefault("0")
    private Integer ReportCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Review> reviewList = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Jjim> jjimList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Block> block_member_List;

    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Report> reportList = new ArrayList<>();


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