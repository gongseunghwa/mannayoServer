package hansung.mannayo.mannayoserverapplication.Model.Entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentToComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Comment.class) @JsonManagedReference
    private Comment comment;

    @NotNull
    private String nickName;

    @NotNull
    private String contents;

    private Integer Depth;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime deletedDate;

    private Boolean isDeleted;

    private Boolean isModified;

    @PrePersist
    public void createAt(){
        this.createdDate = LocalDateTime.now();
        this.isModified = false;
        this.isDeleted = false;
    }
}
