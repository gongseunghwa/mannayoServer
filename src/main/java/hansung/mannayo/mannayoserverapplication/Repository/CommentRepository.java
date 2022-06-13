package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Long countByBoardId(Long id);

    Optional<List<Comment>> findByBoardId(Long id);

    Optional<Comment> findCommentById(Long id);
}
