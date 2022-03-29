package hansung.mannayo.mannayoserverapplication.Model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String NickName;

    @NotNull
    private String Email;

    @NotNull
    private String Password;

    @Enumerated(EnumType.STRING)
    private AccountType accountTypeEnum;

    @NotNull
    private String PhoneNumber;

    @NotNull
    private LocalDate Birth;

    @Enumerated(EnumType.STRING)
    private LoginType loginTypeEnum;

    private String ImageAddress;

    @NotNull()
    @Column(columnDefinition = "integer default 0")
    private Integer ReportCount;


}
