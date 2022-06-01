package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.CommentToComment;
import hansung.mannayo.mannayoserverapplication.Repository.CommentToCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentToCommentServiceImpl implements CommentToCommentService{

    @Autowired
    CommentService commentService;

    @Autowired
    CommentToCommentRepository commentToCommentRepository;

    @Autowired
    MemberService memberService;

    @Override
    public Long countByCommentId(Long id) {
        return commentToCommentRepository.countByCommentId(id);
    }

    @Override
    public Optional<List<CommentToComment>> findByCommentId(Long id) {
        return commentToCommentRepository.findByCommentId(id);
    }

    @Override
    public Boolean setCommentToComment(Long memberid, Long commentid, String contents) {
        if(commentService.findCommentById(commentid).isPresent()) {
            CommentToComment commentToComment = CommentToComment.builder()
                    .nickName(memberService.findbyId(memberid).getNickName())
                    .comment(commentService.findCommentById(commentid).get())
                    .contents(contents)
                    .writerid(memberid)
                    .build();
            commentToCommentRepository.save(commentToComment);
            return true;
        }
        return false;
    }
}
