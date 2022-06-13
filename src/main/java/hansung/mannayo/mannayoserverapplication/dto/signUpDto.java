package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountStatus;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import lombok.*;

import java.time.LocalDate;

@Data @Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class signUpDto {

    private String realname;

    private String NickName;

    private String Email;

    private String Password;

    private AccountType accountTypeEnum;

    private AccountStatus accountStatus;

    private String PhoneNumber;

    private LocalDate Birth;

    private LoginType loginTypeEnum;

    private String ImageAddress;

}
