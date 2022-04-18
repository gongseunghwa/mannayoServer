package hansung.mannayo.mannayoserverapplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter @Getter
public class findMyAccountByPhoneNumberDto {

    private String name;

    private String phoneNumber;
}
