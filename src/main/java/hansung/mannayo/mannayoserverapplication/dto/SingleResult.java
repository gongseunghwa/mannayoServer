package hansung.mannayo.mannayoserverapplication.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SingleResult<T> extends  CommonResult {

    private T data;
}
