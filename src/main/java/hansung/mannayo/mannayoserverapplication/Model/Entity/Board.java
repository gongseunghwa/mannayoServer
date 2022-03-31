package hansung.mannayo.mannayoserverapplication.Model.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Setter @Getter @NoArgsConstructor @Builder
@AllArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JsonManagedReference
    @JoinColumn(name = "writer")
    private Member member;

    @NotNull
    private String title;

    @NotNull
    private String contents;

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

    @Enumerated(EnumType.STRING)
    private BoardType type;


    @PrePersist
    public void createAt(){
        this.createdDate = LocalDateTime.now();
        this.viewCount = 0;
    }

}
