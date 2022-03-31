package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id @GeneratedValue
    private Integer idMessage;

    @NotNull
    @ManyToOne(targetEntity = Member.class)
    private Member member_Sender;

    @NotNull
    @ManyToOne(targetEntity = Member.class)
    private Member member_Receiver;

    @NotNull
    private LocalDate Date;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Boolean isRead;

}

//CREATE TABLE Message (
//  idMessage INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
//  Member_NICKNAME VARCHAR(20)  NOT NULL  ,
//  Date DATE NOT NULL  ,
//  Title VARCHAR(100) NOT NULL  ,
//  Contents VARCHAR(1000) NOT NULL  ,
//  isRead BOOL  NOT NULL    ,
//PRIMARY KEY(idMessage)  ,
//INDEX Message_FKIndex1(Member_NICKNAME)  ,
//INDEX Message_FKIndex2(Member_NICKNAME));