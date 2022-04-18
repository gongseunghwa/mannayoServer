package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountStatus;
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

    private String realName;

    @Column(nullable = false, unique = true)
    private String nickName;

    @NotNull
    private String Email;

    @NotNull
    @JsonIgnore
    private String Password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountTypeEnum;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @NotNull
    private String PhoneNumber;

    @NotNull
    private LocalDate Birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginType loginTypeEnum;

    private String ImageAddress;

    @ColumnDefault("0")
    private Integer passwordFailCount;

    @ColumnDefault("0")
    private Integer ReportCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviewList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Jjim> jjimList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Like> likeList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Block> block_member_List;

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Report> reportList;


    public void addReview(Review review){
        this.reviewList.add(review);
        review.setMember(this);
    }

    public void addBoard(Board board){
        this.boardList.add(board);
        board.setMember(this);
    }

    public void addJjim(Jjim jjim){
        this.jjimList.add(jjim);
        jjim.setMember(this);
    }

    public void addLike(Like like){
        this.likeList.add(like);
        like.setMember(this);
    }

    public void addBlock(Block block){
        this.block_member_List.add(block);
        block.setMember(this);
    }

    public void addReport(Report report){
        this.reportList.add(report);
        report.setMember(this);
    }

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