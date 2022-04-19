package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Service.MemberServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    MemberServiceImpl service;

    private final ResponseService responseService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 제목과 설명 표시
    @GetMapping
    public ResponseEntity<ListResult<Member>> findAll(){
        List<Member> list = service.findAll();
        return ResponseEntity.ok().body(responseService.getListResult(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findbyNickName(@PathVariable Long id){
        Member obj = service.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Member> insert(@RequestBody MemberDto memberDto) {
        System.out.println(memberDto);
        return ResponseEntity.ok(service.insert(memberDto));
    }

    @PostMapping("/findMyAccountByNickname")
    public ResponseEntity<String> findByNameAndNickname(@RequestBody findMyAccountByNicknameDto dto) {
        String email = service.findEmailByNickname(dto);
        System.out.println(email);
        return ResponseEntity.ok().body(email);
    }

    @PostMapping("/findMyAccountByPhoneNumber")
    public ResponseEntity<String> findByNameAndPhoneNumber(@RequestBody findMyAccountByPhoneNumberDto dto) {
        String email = service.findEmailByPhoneNumber(dto);
        return ResponseEntity.ok().body(email);
    }

    @PostMapping("/findMyPasswordByEmail")
    public ResponseEntity findByPasswordByEmail(@RequestBody findMyPasswordByEmailDto dto) {
        boolean isExist = service.findPasswordByEmail(dto);
        return ResponseEntity.ok().body(isExist);
    }

    @PostMapping("/findMyPasswordByPhoneNumber")
    public ResponseEntity findByPasswordByPhoneNumber(@RequestBody findMyPasswordByPhoneNumberDto dto) {
        boolean isExist = service.findPasswordByPhoneNumber(dto);
        return ResponseEntity.ok().body(isExist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberDto obj){
        return ResponseEntity.ok().body(service.update(id,obj));
    }


}
