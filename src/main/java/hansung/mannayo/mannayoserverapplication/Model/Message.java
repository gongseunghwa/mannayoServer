package hansung.mannayo.mannayoserverapplication.Model;

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

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "Member_Nickname")
    private Member member_Sender;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "Meber_Nickname")
    private Member member_Receiver;

    private LocalDate Date;

    private String title;

    private String contents;

    private boolean isRead;

}

//CREATE TABLE Message (
//  idMessage INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
//  Member_NICKNAME VARCHAR(20)  NOT NULL  ,
//  Date DATE  NULL  ,
//  Title VARCHAR(100)  NULL  ,
//  Contents VARCHAR(1000)  NULL  ,
//  isRead BOOL  NULL    ,
//PRIMARY KEY(idMessage)  ,
//INDEX Message_FKIndex1(Member_NICKNAME)  ,
//INDEX Message_FKIndex2(Member_NICKNAME));