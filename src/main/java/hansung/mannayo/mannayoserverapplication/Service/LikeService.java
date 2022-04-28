package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    Optional<List<Like>> findListByMemberId(Long id);

}
