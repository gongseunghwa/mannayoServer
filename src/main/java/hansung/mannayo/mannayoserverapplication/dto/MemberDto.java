package hansung.mannayo.mannayoserverapplication.dto;

import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {

    private String nickName;

    private String passWord;

    private LocalDate birth;

    private String phoneNumber;

    private Integer reportCount;

    private String email;

    private String imageAddress;

    private AccountType accountType;

    private LoginType loginType;

}
