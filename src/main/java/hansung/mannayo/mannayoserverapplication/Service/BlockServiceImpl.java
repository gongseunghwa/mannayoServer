package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Block;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.BlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlockServiceImpl implements BlockService{

    @Autowired
    BlockRepository blockRepository;


    @Override
    public void insert(Member member, Member BlockedMember) {
        Block block = Block.builder()
                .member(member)
                .target_member(BlockedMember)
                .build();

        blockRepository.save(block);
    }

    @Override
    public Optional<List<Block>> findByMemberId(Long id) {
        return blockRepository.findByMember_Id(id);
    }
}
