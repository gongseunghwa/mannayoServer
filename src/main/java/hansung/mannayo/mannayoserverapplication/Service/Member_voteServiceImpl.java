package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.member_vote;
import hansung.mannayo.mannayoserverapplication.Repository.member_voteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Member_voteServiceImpl implements Member_VoteService{
    @Autowired
    member_voteRepository member_voteRepository;

    @Override
    public Optional<member_vote> findMemberVoteByVoteIdAndMemberId(Long voteId, Long memberId) {
        return member_voteRepository.findByVoteIdAndMemberId(voteId, memberId);
    }
}
