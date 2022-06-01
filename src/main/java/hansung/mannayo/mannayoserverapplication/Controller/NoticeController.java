package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Notice;
import hansung.mannayo.mannayoserverapplication.Service.NoticeService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.NoticeDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "알림 정보 가져오기")
    @GetMapping
    public ResponseEntity<List<NoticeDto>> getAlarm(@RequestParam Long memberid) {

        List<NoticeDto> noticeDtos = new ArrayList<>();
        Optional<List<Notice>> notices = noticeService.findNoticeByReceiverId(memberid.toString());
        if(notices.isPresent()) {
            for(Notice n : notices.get()) {
                NoticeDto noticeDto = NoticeDto.builder()
                        .contents(n.getContents())
                        .date(n.getCreatedDate())
                        .title(n.getTitle())
                        .build();
                noticeDtos.add(noticeDto);
            }
        }


        return ResponseEntity.ok().body(noticeDtos);
    }

}
