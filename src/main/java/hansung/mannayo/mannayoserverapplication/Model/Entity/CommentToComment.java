package hansung.mannayo.mannayoserverapplication.Model.Entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentToComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @NotNull
    private String nickName;

    @NotNull
    private String contents;

    private Integer Depth;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private LocalDateTime deletedDate;

    private Boolean isDeleted;

    private Boolean isModified;

    @PrePersist
    public void createAt(){
        this.isModified = false;
        this.isDeleted = false;
    }
}
