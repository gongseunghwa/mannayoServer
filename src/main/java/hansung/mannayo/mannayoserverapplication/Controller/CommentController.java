package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Comment;
import hansung.mannayo.mannayoserverapplication.Model.Entity.CommentToComment;
import hansung.mannayo.mannayoserverapplication.Service.CommentService;
import hansung.mannayo.mannayoserverapplication.Service.CommentToCommentService;
import hansung.mannayo.mannayoserverapplication.dto.CommentDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentToCommentService commentToCommentService;


    @ApiOperation("comment 호출하기")
    @GetMapping
    public ResponseEntity<List<CommentDto>> getComment(@RequestParam Long boardId) {
        CommentDto commentDto = new CommentDto();
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = commentService.getCommentByBoardId(boardId).get();

        for(Comment c : comments) {
            commentDto.setNickname(c.getNickName());
            commentDto.setDate(c.getTime());
            commentDto.setContents(c.getContents());
            commentDto.setDepth(c.getDepth());
            commentDtos.add(commentDto);
            if(commentToCommentService.findByCommentId(c.getId()).isPresent()) {
                List<CommentToComment> commentToCommentList = commentToCommentService.findByCommentId(c.getId()).get();
                for(CommentToComment ctc : commentToCommentList) {
                    commentDto.setNickname(ctc.getNickName());
                    commentDto.setDate(ctc.getCreatedDate());
                    commentDto.setContents(ctc.getContents());
                    commentDto.setDepth(ctc.getDepth());
                    commentDtos.add(commentDto);
                }

            }
        }
        return ResponseEntity.ok().body(commentDtos);
    }

}
