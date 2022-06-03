package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.member_vote;

import java.util.Optional;

public interface Member_VoteService {

    Optional<member_vote> findMemberVoteByVoteIdAndMemberId(Long voteId, Long memberId);

    void insert(member_vote member_vote);

    Long getCount(Long voteid);

}
