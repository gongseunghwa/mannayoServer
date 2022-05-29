package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<List<Vote>> findByBoardId(Long id);



}
