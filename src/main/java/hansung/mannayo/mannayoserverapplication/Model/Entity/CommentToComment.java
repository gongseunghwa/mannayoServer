package hansung.mannayo.mannayoserverapplication.Model.Entity;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentToComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    private String nickName;

    private String contents;
}
