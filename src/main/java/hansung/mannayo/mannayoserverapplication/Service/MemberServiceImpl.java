package hansung.mannayo.mannayoserverapplication.Service;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByPhoneNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    //find all members
    @Override
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    //find member by pk(nickname)
    @Override
    public Member findbyId(Long id){
        Optional<Member> obj = memberRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public String findEmailByNickname(findMyAccountByNicknameDto dto) {
        return memberRepository.findByRealNameAndNickName(dto.getRealName(), dto.getNickName()).getEmail();
    }

//    @Override
//    public String findEmailByPhoneNumber(findMyAccountByPhoneNumberDto dto) {
//        return memberRepository.findByRealNameAndPhoneNumber(dto.getName(), dto.getPhoneNumber()).getEmail();
//    }

    //save member
    @Override
    public Member insert(MemberDto obj){
        Member member = dtoToEntity(obj);
        return memberRepository.save(member);
    }

    //delete member by pk(nickname)
    @Override
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
    @Override
    public Member update(Long id,MemberDto obj){
        try{
            Member entity = memberRepository.getById(id);
            updateData(entity,obj);
            return memberRepository.save(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }


    @Override
    public void updateData(Member entity, MemberDto obj) {
        entity.setNickName(obj.getNickName());
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
        entity.setAccountTypeEnum(obj.getAccountTypeEnum());
        entity.setPhoneNumber(obj.getPhoneNumber());
        entity.setBirth(obj.getBirth());
        entity.setLoginTypeEnum(obj.getLoginTypeEnum());
        entity.setReportCount(obj.getReportCount());
        entity.setImageAddress(obj.getImageAddress());
    }

}
