package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.dto.SingleResult;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Security.JwtTokenProvider;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.exceptions.CEmailSigninFailedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;


@Api(tags = {"1.sign"})
@RequiredArgsConstructor
@RestController
public class SignController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "login", notes = "email login")
    @PostMapping(value = "/signin")
    public ResponseEntity<SingleResult<String>> signin(@ApiParam(value = "회원 Id -> 이메일",required = true) @RequestParam String email,
                                       @ApiParam(value = "비밀번호",required = true) @RequestParam String password){
        Member member = memberRepository.findByEmail(email).orElseThrow(CEmailSigninFailedException::new);
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new CEmailSigninFailedException();
        }
        return ResponseEntity.ok(responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(member.getId()),member.getRoles())));
    }


    @ApiOperation(value = "가입", notes = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<CommonResult> signup(@ApiParam(value = "회원 ID -> 이메일",required = true)@RequestParam String email,
                                 @ApiParam(value = "비밀번호",required = true)@RequestParam String password,
                                 @ApiParam(value = "이름",required = true)@RequestParam String name){
        memberRepository.save(Member.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .nickName("")
                        .realName(name)
                        .accountTypeEnum(AccountType.MEMBER)
                        .phoneNumber("")
                        .birth(LocalDate.now())
                        .loginTypeEnum(LoginType.EMAIL)
                        .roles(Collections.singletonList("ROLE_USER"))
                .build());

        return ResponseEntity.ok().body(responseService.getSuccessResult());
    }
}
