package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Like;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.dto.LikeDto;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    Optional<List<Like>> findListByMemberId(Long id);

    public void insertLike(Member member, Board board);


}
