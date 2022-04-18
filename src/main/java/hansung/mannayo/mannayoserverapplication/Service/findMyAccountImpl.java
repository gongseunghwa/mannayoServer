package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class findMyAccountImpl implements findMyAccountService{

    private final MemberRepository memberRepository;

    @Override
    public String findEmail(findMyAccountByNicknameDto dto) {
        System.out.println("서비스" + dto);
        return memberRepository.findByRealNameAndNickName(dto.getRealName(), dto.getNickName()).getEmail();
    }
}
