package hansung.mannayo.mannayoserverapplication.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SingleSignInResult<T> extends  CommonResult {

    private T data;

    private String nickname;

    private Long Id;
}
