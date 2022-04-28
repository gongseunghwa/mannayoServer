package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.LikeRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.BoardService;
import hansung.mannayo.mannayoserverapplication.Service.LikeService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.dto.LikeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("게시물 좋아요")
    @PostMapping
    public void likeUp(@RequestBody LikeDto likeDto) { // 좋아요 찍기
        Member member = memberService.findbyId(likeDto.getMember_id());
        Board board = boardService.findById(likeDto.getBoard_id()).get();

        likeService.insertLike(member, board);
    }

}
