package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.member_vote;

import java.util.Optional;

public interface Member_VoteService {

    Optional<member_vote> findMemberVoteByVoteIdAndMemberId(Long voteId, Long memberId);

}
