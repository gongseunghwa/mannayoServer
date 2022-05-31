package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Vote;
import hansung.mannayo.mannayoserverapplication.Service.Member_VoteService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.Service.VoteService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.VoteResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    Member_VoteService member_voteService;

    @Autowired
    ResponseService responseService;

    @ApiOperation(value = "response vote", notes = "게시판 별 투표 호출")
    @GetMapping("/{boardid}")
    public ResponseEntity<List<VoteResponseDto>> getVote(@PathVariable("boardid") Long id, @RequestParam Long memberId) {
        List<Vote> voteList = voteService.getVoteByBoardId(id).get();
        List<VoteResponseDto> voteResponseDtos = new ArrayList<>();
        VoteResponseDto voteResponseDto;
        for (Vote v : voteList) {
            if (member_voteService.findMemberVoteByVoteIdAndMemberId(v.getId(), memberId).isPresent()) {
               voteResponseDto = VoteResponseDto.builder()
                        .contents(v.getContents())
                        .Count(v.getCount())
                        .amIVote(true)
                        .build();
            }else {
                voteResponseDto = VoteResponseDto.builder()
                        .contents(v.getContents())
                        .Count(v.getCount())
                        .amIVote(false)
                        .build();
            }
            voteResponseDtos.add(voteResponseDto);
        }

        return ResponseEntity.ok().body(voteResponseDtos);
    }

    @ApiOperation(value = "request vote", notes = "게시판 별 투표 입력")
    @PostMapping("/{boardid}")
    public ResponseEntity<CommonResult> setVote(@PathVariable("boardid") Long id, @RequestParam String contents) {

        System.out.println(contents);
        Vote vote = voteService.insertVote(id, contents);

        if(vote != null) {
            CommonResult commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }

        CommonResult commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

}