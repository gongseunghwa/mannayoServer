package hansung.mannayo.mannayoserverapplication.Service;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;

import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByPhoneNumberDto;
import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import hansung.mannayo.mannayoserverapplication.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.thymeleaf.util.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


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
    public Member findbyNickname(String nickname){
        Optional<Member> obj = memberRepository.findByNickName(nickname);
        return obj.orElseThrow(() -> new ResourceNotFoundException(nickname));
    }

    @Override
    public String findEmailByNickname(findMyAccountByNicknameDto dto) {
        return memberRepository.findByRealNameAndNickName(dto.getRealName(), dto.getNickName()).getEmail();
    }

    @Override
    public String findEmailByPhoneNumber(findMyAccountByPhoneNumberDto dto) {
        return memberRepository.findByRealNameAndPhoneNumber(dto.getRealName(), dto.getPhoneNumber()).getEmail();
    }

    @Override
    public boolean findPasswordByEmail(findMyPasswordByEmailDto dto) {
        return (memberRepository.findByRealNameAndEmail(dto.getName(), dto.getEmail()) != null);
    }

    @Override
    public boolean findPasswordByPhoneNumber(findMyPasswordByPhoneNumberDto dto) {
        return (memberRepository.findByRealNameAndPhoneNumber(dto.getName(), dto.getPhoneNumber()) != null);
    }

    @Override
    public Optional<String> getImageAddress(Long writerId) {
        return memberRepository.findImageAddress(writerId);
    }

    @Override
    public Boolean updateNickname(Long id, String nickname) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            member.get().setNickName(nickname);
            memberRepository.save(member.get());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean updateFCMtoken(Long id, String token) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            member.get().setToken(token);
            memberRepository.save(member.get());
            return true;
        }
        return false;
    }

    //save member
    @Override
    public void insert(signUpDto obj){
        Member member = Member.builder()
                .realName(obj.getRealname())
                .accountStatus(obj.getAccountStatus())
                .loginTypeEnum(obj.getLoginTypeEnum())
                .accountTypeEnum(obj.getAccountTypeEnum())
                .nickName("null")
                .password(passwordEncoder.encode(obj.getPassword()))
                .email(obj.getEmail())
                .phoneNumber(obj.getPhoneNumber())
                .imageAddress(obj.getImageAddress())
                .birth(obj.getBirth())
                .build();
        member = memberRepository.save(member);
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
    public void updateImageAddress(Member member) {
        memberRepository.save(member);
    }

    @Override
    public List<String> getToken() {
        List<String> tokens = memberRepository.findToken().get();
        List<String> deviceToken = new ArrayList<>();
        for(String s : tokens) {
            if(!isEmpty(s)) {
                System.out.println(s);
                deviceToken.add(s);
            }
        }
        return deviceToken;
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
