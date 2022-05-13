package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.ListResult;
import hansung.mannayo.mannayoserverapplication.dto.SingleSignInResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    MemberRepository memberRepository;

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public enum CommonResponse{
        SUCCESS(0, "성공"),
        FAIL(-1, "실패");

        private int code;
        private String msg;
    }


    //단일건 결과를 처리하는 메소드
    public <T> SingleSignInResult<T> getSingleSuccessResult(T data) {
        SingleSignInResult<T> result = new SingleSignInResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> SingleSignInResult<T> getSingleFailResult(T data) {
        SingleSignInResult<T> result = new SingleSignInResult<>();
        result.setSuccess(false); // setSuccess : 응답 성공 여부 (true/false)
        result.setCode(CommonResponse.FAIL.getCode()); // setCode : 응답 코드 번호 >= 0 정상, < 0 비정상
        result.setMsg(CommonResponse.FAIL.getMsg()); // setMsg 응답 메시지
        result.setData(data);
        return result;
    }

    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() { // CommonResult 응답 결과를 알려주는 클래스
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false); // setSuccess : 응답 성공 여부 (true/false)
        result.setCode(CommonResponse.FAIL.getCode()); // setCode : 응답 코드 번호 >= 0 정상, < 0 비정상
        result.setMsg(CommonResponse.FAIL.getMsg()); // setMsg 응답 메시지
        return result;
    }

    // 결과 모델에 API 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

}
