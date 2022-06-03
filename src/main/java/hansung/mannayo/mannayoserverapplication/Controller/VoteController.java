package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Vote;
import hansung.mannayo.mannayoserverapplication.Model.Entity.member_vote;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
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

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "response vote", notes = "게시판 별 투표 호출")
    @GetMapping("/{boardid}")
    public ResponseEntity<List<VoteResponseDto>> getVote(@PathVariable("boardid") Long id, @RequestParam Long memberId) {
        List<Vote> voteList = voteService.getVoteByBoardId(id).get();
        List<VoteResponseDto> voteResponseDtos = new ArrayList<>();
        VoteResponseDto voteResponseDto;

        for (Vote v : voteList) {
            Long count = 0L;
            count = member_voteService.getCount(v.getId());
            if (member_voteService.findMemberVoteByVoteIdAndMemberId(v.getId(), memberId).isPresent()) {
               voteResponseDto = VoteResponseDto.builder()
                        .contents(v.getContents())
                        .Count(count)
                        .amIVote(true)
                        .id(v.getId())
                        .build();
            }else {
                voteResponseDto = VoteResponseDto.builder()
                        .contents(v.getContents())
                        .Count(count)
                        .amIVote(false)
                        .id(v.getId())
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

    @ApiOperation(value = "투표 하기")
    @PostMapping("/tovote")
    public ResponseEntity<CommonResult> setToVote(@RequestParam Long memberid, @RequestParam Long voteid) {
        CommonResult commonResult = new CommonResult();
        member_vote Member_vote = member_vote.builder()
                .member(memberService.findbyId(memberid))
                .vote(voteService.findById(voteid))
                .build();

        member_voteService.insert(Member_vote);

        if(member_voteService.findMemberVoteByVoteIdAndMemberId(memberid,voteid).isPresent()) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }
        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

}