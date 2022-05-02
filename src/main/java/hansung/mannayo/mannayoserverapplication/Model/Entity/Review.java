package hansung.mannayo.mannayoserverapplication.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id @GeneratedValue
    private Long id;

    String title;

    String content;

    LocalDateTime writeDate;

    String image;

    Float starPoint;

    @Column(columnDefinition = "boolean default false")
    Boolean isDeleted;

    @Column(columnDefinition = "boolean default false")
    Boolean isModified;

    LocalDateTime ModifiedDate;
    LocalDateTime DeletedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    Member member;

    @ManyToOne(cascade = CascadeType.ALL) @JsonManagedReference
    Restaurant restaurant;

    @PrePersist
    public void createAt(){
        this.writeDate = LocalDateTime.now();
    }


}
