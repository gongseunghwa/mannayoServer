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
    CommentToCommentRepository commentToCommentRepository;

    @Override
    public Long countByCommentId(Long id) {
        return commentToCommentRepository.countByCommentId(id);
    }

    @Override
    public Optional<List<CommentToComment>> findByCommentId(Long id) {
        return commentToCommentRepository.findByCommentId(id);
    }
}
