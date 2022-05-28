package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.CommentToComment;

import java.util.List;
import java.util.Optional;

public interface CommentToCommentService {

    Long countByCommentId(Long id);

    Optional<List<CommentToComment>> findByCommentId(Long id);

}
