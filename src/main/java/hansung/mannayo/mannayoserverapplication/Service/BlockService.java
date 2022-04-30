package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Block;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface BlockService {

    void insert(Member member, Member BlockedMember);

    Optional<List<Block>> findByMemberId(Long id);

}
