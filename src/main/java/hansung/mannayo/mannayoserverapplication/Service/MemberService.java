package hansung.mannayo.mannayoserverapplication.Service;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.Service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    //find all members
    public List<Member> findAll(){
        return  memberRepository.findAll();
    }

    //find member by pk(nickname)
    public Member findbyNickName(String nickName){
        Optional<Member> obj = memberRepository.findById(nickName);
        return obj.orElseThrow(() -> new ResourceNotFoundException(nickName));
    }
    //save member
    public Member insert(Member obj){
        return memberRepository.save(obj);
    }

    //delete member by pk(nickname)
    public void delete(String nickName){
        try{
            memberRepository.deleteById(nickName);
        }catch( EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(nickName);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    //update Member by pk(nickname)
    public Member update(String nickName,Member obj){
        try{
            Member entity = memberRepository.getById(nickName);
            updateData(entity,obj);
            return memberRepository.save(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(nickName);
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
