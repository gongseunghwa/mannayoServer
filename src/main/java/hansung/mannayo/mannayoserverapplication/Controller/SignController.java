package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.Model.Type.AccountType;
import hansung.mannayo.mannayoserverapplication.Model.Type.LoginType;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Security.JwtTokenProvider;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.SingleSignInResult;
import hansung.mannayo.mannayoserverapplication.dto.signUpDto;
import hansung.mannayo.mannayoserverapplication.exceptions.CEmailSigninFailedException;
import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;


@Api(tags = {"1.sign"})
@RequiredArgsConstructor
@RestController
public class SignController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @ApiOperation(value = "login", notes = "email login")
    @GetMapping(value = "/signin")
    public ResponseEntity<SingleSignInResult<String>> signin(@ApiParam(value = "회원 Id -> 이메일",required = true) @RequestParam String email,
                                                             @ApiParam(value = "비밀번호",required = true) @RequestParam String password){
        Optional<Member> member = memberRepository.findByEmail(email);
        SingleSignInResult<String> result = new SingleSignInResult<>();
        if(member.isEmpty()) {
            String fail = "Email is Not Exist";
            result = responseService.getSingleFailResult(fail);
            return ResponseEntity.ok(result);
        }else {
            if (!passwordEncoder.matches(password, member.get().getPassword())) {
                String fail = "password unmatched";
                result = responseService.getSingleFailResult(fail);
                return ResponseEntity.ok(result);
            }
        }
        result = responseService.getSingleSuccessResult(jwtTokenProvider.createToken(String.valueOf(member.get().getId()),member.get().getRoles()));
        result.setNickname(member.get().getNickName());
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "가입", notes = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<CommonResult> signup(@RequestBody signUpDto signUpDto){
        try {
            memberService.insert(signUpDto);
            return ResponseEntity.ok().body(responseService.getSuccessResult());
        }catch (DatabaseException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
