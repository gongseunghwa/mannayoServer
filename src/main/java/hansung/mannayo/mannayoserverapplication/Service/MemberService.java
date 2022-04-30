package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.dto.*;

import java.util.List;

public interface MemberService {

    public List<Member> findAll();

    //find member by pk(id)
    public Member findbyId(Long id);
    //find member by nickname(nickname)
    public Member findbyNickname(String nickname);
    //save member
    public Member insert(MemberDto obj);

    //delete member by pk(nickname)
    public void delete(Long id);

    //update Member by pk(nickname)
    public Member update(Long id,MemberDto obj);

    public void updateData(Member entity, MemberDto obj);

    public String findEmailByNickname(findMyAccountByNicknameDto dto);

    public String findEmailByPhoneNumber(findMyAccountByPhoneNumberDto dto);

    public boolean findPasswordByEmail(findMyPasswordByEmailDto dto);

    public boolean findPasswordByPhoneNumber(findMyPasswordByPhoneNumberDto dto);

    public void updateImageAddress(Member member);

    default Member dtoToEntity(MemberDto memberDto) {
        Member entity = Member.builder()
                .nickName(memberDto.getNickName())
                .accountTypeEnum(memberDto.getAccountTypeEnum())
                .loginTypeEnum(memberDto.getLoginTypeEnum())
                .phoneNumber(memberDto.getPhoneNumber())
                .birth(memberDto.getBirth())
                .email(memberDto.getEmail())
                .ReportCount(memberDto.getReportCount())
                .imageAddress(memberDto.getImageAddress())
                .password((memberDto.getPassword()))
                .build();

        return entity;
    }
}
