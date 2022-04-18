package hansung.mannayo.mannayoserverapplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter @Getter
public class findMyPasswordByPhoneNumberDto {

    private String email;

    private String name;

    private String phoneNumber;

}
