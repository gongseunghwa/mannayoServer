package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.CommentToComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentToCommentRepository extends JpaRepository<CommentToComment,Long> {
    Long countByCommentId(Long id);

    Optional<List<CommentToComment>> findByCommentId(Long id);
}
