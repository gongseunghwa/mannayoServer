package hansung.mannayo.mannayoserverapplication.Model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Setter @Getter @NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "board")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne @JsonManagedReference
    private Member member;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "contents")
    @NotNull
    private String contents;

    @Column(name = "image")
    private String image;


    private LocalDateTime createdDate;


    private LocalDateTime modifiedDate;

    private LocalDateTime deletedDate;

  //  @Column(columnDefinition = "boolean default false ")
    private Boolean isModified;

   // @Column(columnDefinition = "boolean default false ")
    private Boolean isDeleted;

    private Boolean isVote;

    private Integer viewCount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BoardType type;

    @OneToMany(mappedBy = "board")
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @JsonBackReference
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy ="board")
    @JsonBackReference
    private List<Vote> voteList = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @JsonBackReference
    private List<Report> reportList = new ArrayList<>();

    public void addVote(Vote vote){
        voteList.add(vote);
        vote.setBoard(this);
    }

    @PrePersist
    public void createAt(){
        this.createdDate = LocalDateTime.now();
        this.viewCount = 0;
        this.isModified = false;
        this.isDeleted = false;
    }

}
