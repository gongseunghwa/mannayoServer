package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(targetEntity = Board.class) @JsonManagedReference
    private Board board;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime time;

    @NotNull
    @Column(name = "nickname")
    private String nickName;

    @Column(name = "contents")
    private String contents;

    @Column(name = "depth")
    private Integer Depth;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modifiedDate;

    @Column(name="delete_date")
    private LocalDateTime deletedDate;

    @Column(name = "is_modified")
    private Boolean isModified;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CommentToComment> commentToCommentList;

    public void addCommentToComment(CommentToComment commentToComment){
        commentToCommentList.add(commentToComment);
        commentToComment.setComment(this);
    }

    @PrePersist
    public void createAt(){
        this.Depth = 1;
        this.isDeleted = false;
        this.isModified = false;
    }




}
