package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Type.NoticeType;
import hansung.mannayo.mannayoserverapplication.Service.FCMService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.Service.NoticeService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/push")
public class PushApiController {

    @Autowired
    ResponseService responseService;

    @Autowired
    MemberService memberService;

    @Autowired
    FCMService fcmService;

    @Autowired
    NoticeService noticeService;


    @PostMapping("/notice")
    public ResponseEntity<?> reqFcm(
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String body
    ) {

        log.info("** title : {}",title);
        log.info("** body : {}",body);

        CommonResult res;

        String sender = "ADMINSTRATOR";
        NoticeType noticeType = NoticeType.Notice;
        try {

            List<String> tokens = memberService.getToken();
            for(String token : tokens) {
                fcmService.sendMessageTo(token,title,body);
                Member member = memberService.findByToken(token).get();
                noticeService.insert(memberService.findByToken(token).get().getId().toString(), sender, title, body,noticeType);
            }

            res = responseService.getSuccessResult();
        } catch(Exception e) {
            res = responseService.getFailResult();
            res.setMsg("처리중 에러 발생");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(res);
    }

}
