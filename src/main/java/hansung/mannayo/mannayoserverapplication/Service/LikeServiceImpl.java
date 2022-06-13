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

import static org.springframework.util.ObjectUtils.isEmpty;

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
    public Boolean isLikeExistAlready(Long memberId, Long boardId) {
        Optional<Like> like = likeRepository.findByMemberIdAndBoardId(memberId, boardId);
        if(like.isPresent()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void deleteLike(Long memberId, Long boardId) {
        Like like = likeRepository.findByMemberIdAndBoardId(memberId, boardId).get();
        likeRepository.delete(like);
    }

    @Override
    public Boolean insertLike(Member member, Board board) {
        Like like = Like.builder()
                .member(member)
                .board(board)
                .build();
        Like save = likeRepository.save(like);

        if(isEmpty(save)) {
            return false;
        }
        else {
            return true;
        }
    }
}
