package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteService {
    Optional<List<Vote>> getVoteByBoardId(Long id);

    Vote insertVote(Long id, String contents);

    Vote findById(Long id);
}
