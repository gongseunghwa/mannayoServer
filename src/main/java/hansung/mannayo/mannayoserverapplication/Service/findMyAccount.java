package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class findMyAccount implements findMyAccountService{

    private final MemberRepository memberRepository;

    @Override
    public String findEmail(findMyAccountByNicknameDto dto) {
        return memberRepository.findByRealNameAndNickName(dto.getRealName(), dto.getNickName()).getEmail();
    }
}
