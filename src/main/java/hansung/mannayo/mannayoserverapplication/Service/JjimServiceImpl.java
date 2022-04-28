package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Repository.JjimRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JjimServiceImpl implements JjimService{

    @Autowired
    JjimRepository jjimRepository;

    @Override
    public Optional<List<Jjim>> findByMemberId(Long id) {
        return jjimRepository.findByMember_Id(id);
    }
}
