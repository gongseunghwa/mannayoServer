package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Block;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Service.BlockService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.dto.ListResult;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByPhoneNumberDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import hansung.mannayo.mannayoserverapplication.dto.*;
import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    BlockService blockService;

    private final ResponseService responseService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 제목과 설명 표시
    @GetMapping
    public ResponseEntity<ListResult<Member>> findAll(){
        List<Member> list = memberService.findAll();
        return ResponseEntity.ok().body(responseService.getListResult(list));
    }

    @ApiOperation(value = "닉네임으로 회원 찾기")
    @GetMapping("/{id}")
    public ResponseEntity<Member> findbyNickName(@PathVariable Long id){
        Member obj = memberService.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "회원가입하기")
    @PostMapping
    public ResponseEntity<Member> insert(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.insert(memberDto));
    }

    @ApiOperation(value = "닉네임으로 계정 찾기")
    @PostMapping("/findMyAccountByNickname")
    public ResponseEntity<findMyAccountByNicknameResponseDto> findByNameAndNickname(@RequestBody findMyAccountByNicknameDto dto) {
        System.out.println(dto);
        findMyAccountByNicknameResponseDto responsedto = new findMyAccountByNicknameResponseDto();
        responsedto.setEmail(memberService.findEmailByNickname(dto));
        System.out.println(responsedto.getEmail());
        return ResponseEntity.ok().body(responsedto);
    }

    @ApiOperation(value = "휴대폰번호로 계정찾기")
    @PostMapping("/findMyAccountByPhoneNumber")
    public ResponseEntity<findMyAccountByPhoneNumberResponseDto> findByNameAndPhoneNumber(@RequestBody findMyAccountByPhoneNumberDto dto) {
        System.out.println(dto);
        findMyAccountByPhoneNumberResponseDto responsedto = new findMyAccountByPhoneNumberResponseDto();
        responsedto.setEmail(memberService.findEmailByPhoneNumber(dto));
        System.out.println(responsedto.getEmail());
        return ResponseEntity.ok().body(responsedto);
    }

    @ApiOperation(value = "이메일로 잊어버린 비밀번호 변경하기")
    @PostMapping("/findMyPasswordByEmail")
    public ResponseEntity findByPasswordByEmail(@RequestBody findMyPasswordByEmailDto dto) {
        boolean isExist = memberService.findPasswordByEmail(dto);
        return ResponseEntity.ok().body(isExist);
    }

    @ApiOperation(value = "휴대폰 번호로 잊어버린 비밀번호 변경하기")
    @PostMapping("/findMyPasswordByPhoneNumber")
    public ResponseEntity findByPasswordByPhoneNumber(@RequestBody findMyPasswordByPhoneNumberDto dto) {
        boolean isExist = memberService.findPasswordByPhoneNumber(dto);
        return ResponseEntity.ok().body(isExist);
    }


    @ApiOperation(value = "계정삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "계정 수정하기")
    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberDto obj){
        return ResponseEntity.ok().body(memberService.update(id,obj));
    }

    @ApiOperation(value = "차단하기")
    @PostMapping("/block")
    public void blockMember(@RequestBody BlockDto blockDto) {

        Member member = memberService.findbyId(blockDto.getMemberId());
        Member blockMember = memberService.findbyId(blockDto.getBlockedMemberId());

        blockService.insert(member, blockMember);

    }

    @ApiOperation(value = "차단내역 불러오기")
    @GetMapping("/block")
    public ResponseEntity<List<BlockResponseDto>> showBlockMember(@RequestParam Long id) {

        List<Block> blocks = blockService.findByMemberId(id).get();
        List<BlockResponseDto> blockResponseDtos = new ArrayList<>();
        BlockResponseDto blockResponseDto = new BlockResponseDto();

        for(int i=0; i< blocks.size();i++) {
            blockResponseDto.setMemberId(blocks.get(i).getTarget_member().getId());
            blockResponseDto.setNickname(blocks.get(i).getTarget_member().getNickName());
            blockResponseDtos.add(blockResponseDto);
        }

        return ResponseEntity.ok().body(blockResponseDtos);
    }




}
