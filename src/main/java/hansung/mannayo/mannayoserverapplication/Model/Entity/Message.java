package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
public class Message {

    @Id @GeneratedValue
    private Long idMessage;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Member member_Sender;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Member member_Receiver;

    @NotNull
    @CreatedDate
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