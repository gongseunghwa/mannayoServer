package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member loadUserByUsername(String userPk){
        return memberRepository.findById(Long.valueOf(userPk)).orElseThrow();
    }
}
