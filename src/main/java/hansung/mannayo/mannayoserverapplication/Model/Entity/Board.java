package hansung.mannayo.mannayoserverapplication.Model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Setter @Getter @NoArgsConstructor
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) //현재 Entity에 Auditing 기능을 포함시키기 위한 어노테이션
@Table(name = "boards")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "writer_id")
    @ManyToOne @JsonManagedReference
    private Member member;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    private String image;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @Column(name = "modify_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name="delete_date")
    private LocalDateTime deletedDate;

  //  @Column(columnDefinition = "boolean default false ")
    @Column(name = "isModified")
    private Boolean isModified;

    // @Column(columnDefinition = "boolean default false ")
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "isVote")
    private Boolean isVote;

    @Column(name = "viewCount")
    private Integer viewCount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Board_type")
    private BoardType type;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Like> likeList;

    @OneToMany(mappedBy ="board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Vote> voteList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Report> reportList;

    public void addVote(Vote vote){
        voteList.add(vote);
        vote.setBoard(this);
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setBoard(this);
    }
    public void addLike(Like like){
        likeList.add(like);
        like.setBoard(this);
    }

    public void addReport(Report report){
        reportList.add(report);
        report.setBoard(this);
    }

    @PrePersist
    public void createAt(){
        this.viewCount = 0;
        this.isModified = false;
        this.isDeleted = false;
    }

}
