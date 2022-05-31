package hansung.mannayo.mannayoserverapplication.Model.Entity;

import hansung.mannayo.mannayoserverapplication.Model.Type.NoticeType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder @Table(name = "notice")
@EntityListeners(AuditingEntityListener.class) //현재 Entity에 Auditing 기능을 포함시키기 위한 어노테이션
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @CreatedDate
    private LocalDateTime createdDate;

    @NotNull
    private String sender;

    @NotNull
    private String receiver;

    private String title;

    private String contents;


}
