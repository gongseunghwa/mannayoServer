package hansung.mannayo.mannayoserverapplication.Model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member {

    @Id
    private String NickName;

    @NotNull
    private String Email;

    @NotNull
    private String Password;

    @Enumerated(EnumType.STRING)
    private Enum<AccountType> accountTypeEnum;

    @NotNull
    private String PhoneNumber;

    @NotNull
    private Date Birth;

    @Enumerated(EnumType.STRING)
    private Enum<LoginType> loginTypeEnum;

    private String ImageAddress;

    @NotNull()
    @Column(columnDefinition = "integer default 0")
    private Integer ReportCount;

}
