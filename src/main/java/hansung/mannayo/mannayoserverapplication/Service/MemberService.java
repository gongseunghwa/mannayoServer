package hansung.mannayo.mannayoserverapplication.Service;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.Service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    //find all members
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    //find member by pk(nickname)
    public Member findbyId(Long id){
        Optional<Member> obj = memberRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    //save member
    public Member insert(Member obj){
        return memberRepository.save(obj);
    }

    //delete member by pk(nickname)
    public void delete(Long id){
        try{
            memberRepository.deleteById(id);
        }catch( EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    //update Member by pk(nickname)
    public Member update(Long id,Member obj){
        try{
            Member entity = memberRepository.getById(id);
            updateData(entity,obj);
            return memberRepository.save(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Member entity, Member obj) {
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
        entity.setAccountTypeEnum(obj.getAccountTypeEnum());
        entity.setPhoneNumber(obj.getPhoneNumber());
        entity.setBirth(obj.getBirth());
        entity.setLoginTypeEnum(obj.getLoginTypeEnum());
        entity.setReportCount(obj.getReportCount());
    }

}
