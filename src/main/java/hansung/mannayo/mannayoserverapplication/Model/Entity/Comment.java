package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "Board_id")
    private Board board;

    private LocalDateTime time;

    @NotNull
    private String nickName;

    private String contents;

    private Integer Depth;

    private LocalDateTime modifiedDate;

    private LocalDateTime deletedDate;

    private Boolean isModified;

    private Boolean isDeleted;

    @PrePersist
    public void createAt(){
        this.Depth = 1;
        this.time = LocalDateTime.now();
        this.isDeleted = false;
        this.isModified = false;
    }




}
