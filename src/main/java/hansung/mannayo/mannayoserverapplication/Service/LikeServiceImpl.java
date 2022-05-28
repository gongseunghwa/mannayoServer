package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Like;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.LikeRepository;
import hansung.mannayo.mannayoserverapplication.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    @Autowired
    LikeRepository likeRepository;

    @Override
    public Optional<List<Like>> findListByMemberId(Long id) {
        return likeRepository.findByMember_Id(id);
    }

    @Override
    public Long getCountLike(Long id) {
        return likeRepository.countLikeByBoardId(id);
    }


    @Override
    public void insertLike(Member member, Board board) {
        Like like = Like.builder()
                .member(member)
                .board(board)
                .build();
        likeRepository.save(like);

    }
}
