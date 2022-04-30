package hansung.mannayo.mannayoserverapplication.Repository;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JjimRepository extends JpaRepository<Jjim, Long> {

    Optional<List<Jjim>> findByMember_Id(Long id);

}
