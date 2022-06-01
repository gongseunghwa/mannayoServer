package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Service.FCMService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PushApiController {

    @Autowired
    ResponseService responseService;

    @Autowired
    FCMService fcmService;


    @PostMapping("/fcm")
    public ResponseEntity<?> reqFcm(
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String body
    ) {

        log.info("** title : {}",title);
        log.info("** body : {}",body);

        CommonResult res;

        try {
//            String token = fcmService.getAccessToken();
            String token = "cUp46AMORSSeYImQ7GmXrk:APA91bHPl4mFHSwl_Mufilaj63_XMYKN6ewEFh5vF3F8nvUDsH5Z7XpDb-GNHnU_fv7WxD5-0bF8RvmKQqvt8_43g1HT8abDc3F0iAphli00wp73tbG8fGoGqGdHgCmy9s3aQAPe-5xY";
            fcmService.sendMessageTo(token,title,body);
            res = responseService.getSuccessResult();

        } catch(Exception e) {
            res = responseService.getFailResult();
            res.setMsg("처리중 에러 발생");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(res);
    }

}
