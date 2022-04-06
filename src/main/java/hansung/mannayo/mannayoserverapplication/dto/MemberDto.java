package hansung.mannayo.mannayoserverapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @Getter @Setter
public class MemberDto {

    private Long id;

    private String NickName;

    private String Email;

    private String Password;

    private AccountType accountTypeEnum;

    private String PhoneNumber;

    private LocalDate Birth;

    private LoginType loginTypeEnum;

    private String ImageAddress;

    private Integer ReportCount;
}
