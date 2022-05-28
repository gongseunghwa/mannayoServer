package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Comment;
import hansung.mannayo.mannayoserverapplication.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Long getCountCommentByBoardId(Long id) {
        return commentRepository.countByBoardId(id);
    }


    @Override
    public Optional<List<Comment>> getCommentByBoardId(Long id) {
        return commentRepository.findByBoardId(id);
    }
}
