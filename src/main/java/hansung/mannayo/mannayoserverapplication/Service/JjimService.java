package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;

import java.util.List;
import java.util.Optional;

public interface JjimService {

    Optional<List<Jjim>> findByMemberId(Long id);

}
