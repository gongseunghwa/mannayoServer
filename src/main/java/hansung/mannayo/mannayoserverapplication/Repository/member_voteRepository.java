package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.member_vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface member_voteRepository extends JpaRepository<member_vote, Long> {

    Optional<member_vote> findByVoteIdAndMemberId(Long voteId, Long memberId);

    Long countByVoteId(Long voteid);

}
