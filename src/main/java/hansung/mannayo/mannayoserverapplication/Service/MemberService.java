package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;

import java.util.List;

public interface MemberService {

    public List<Member> findAll();

    //find member by pk(nickname)
    public Member findbyId(Long id);
    //save member
    public Member insert(MemberDto obj);

    //delete member by pk(nickname)
    public void delete(Long id);

    //update Member by pk(nickname)
    public Member update(Long id,MemberDto obj);

    public void updateData(Member entity, MemberDto obj);

    public String findEmail(findMyAccountByNicknameDto dto);

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
