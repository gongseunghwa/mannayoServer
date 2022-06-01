package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.LikeRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.BoardService;
import hansung.mannayo.mannayoserverapplication.Service.LikeService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.LikeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    LikeService likeService;

    @Autowired
    MemberService memberService;

    @Autowired
    BoardService boardService;

    @Autowired
    ResponseService responseService;

    @ApiOperation("게시물 좋아요 또는 해제")
    @PostMapping
    public ResponseEntity<CommonResult> likeUp(@RequestParam Long memberid, @RequestParam Long boardid) { // 좋아요 찍기
        CommonResult commonResult = new CommonResult();

        if(likeService.isLikeExistAlready(memberid, boardid))  {
            likeService.deleteLike(memberid,boardid);
            commonResult = responseService.getSuccessResult();
            commonResult.setMsg("게시물 좋아요가 취소되었습니다.");
            return ResponseEntity.ok().body(commonResult);
        }

        Member member = memberService.findbyId(memberid);
        Board board = boardService.findById(boardid).get();
        Boolean save = likeService.insertLike(member, board);

        if(save) {
            commonResult = responseService.getSuccessResult();
            commonResult.setMsg("게시물 좋아요가 등록되었습니다.");
            return ResponseEntity.ok().body(commonResult);
        }

        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

}
