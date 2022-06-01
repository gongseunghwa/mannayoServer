package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Comment;
import hansung.mannayo.mannayoserverapplication.Model.Entity.CommentToComment;
import hansung.mannayo.mannayoserverapplication.Service.CommentService;
import hansung.mannayo.mannayoserverapplication.Service.CommentToCommentService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.CommentDto;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentToCommentService commentToCommentService;

    @Autowired
    ResponseService responseService;


    @ApiOperation("comment 호출하기")
    @GetMapping
    public ResponseEntity<List<CommentDto>> getComment(@RequestParam Long boardId) {
        CommentDto commentDto = new CommentDto();
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = commentService.getCommentByBoardId(boardId).get();

        for(Comment c : comments) {
            commentDto = CommentDto.builder()
                    .id(c.getId())
                    .nickname(c.getNickName())
                    .date(c.getTime())
                    .contents(c.getContents())
                    .depth(c.getDepth())
                    .build();
            commentDtos.add(commentDto);
            if(commentToCommentService.findByCommentId(c.getId()).isPresent()) {
                List<CommentToComment> commentToCommentList = commentToCommentService.findByCommentId(c.getId()).get();
                for(CommentToComment ctc : commentToCommentList) {
                    commentDto = CommentDto.builder()
                            .id(c.getId())
                            .nickname(c.getNickName())
                            .date(c.getTime())
                            .contents(c.getContents())
                            .depth(c.getDepth())
                            .build();
                    commentDtos.add(commentDto);
                }

            }
        }
        return ResponseEntity.ok().body(commentDtos);
    }

    @ApiOperation(value = "댓글 작성")
    @PostMapping(value = "/inputReply")
    public ResponseEntity<CommonResult> setBoardReply(@RequestParam Long memberid, @RequestParam Long boardid, @RequestParam String contents) {
        CommonResult commonResult = new CommonResult();
        if(commentService.setComment(memberid, boardid, contents)) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }
        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

    @ApiOperation(value = "eo댓글 작성")
    @PostMapping(value = "/inputReplyOfReply")
    public ResponseEntity<CommonResult> setBoardReply2(@RequestParam Long memberid, @RequestParam Long commentid, @RequestParam String contents) {
        CommonResult commonResult = new CommonResult();
        if(commentToCommentService.setCommentToComment(memberid, commentid, contents)) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }
        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

}
