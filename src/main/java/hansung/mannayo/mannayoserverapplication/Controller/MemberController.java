package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Service.MemberServiceImpl;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByPhoneNumberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    MemberServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Member>> findAll(){
        List<Member> list = service.findAll();
        return ResponseEntity.ok().body(list);
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
        return ResponseEntity.ok().body(email);
    }

//    @PostMapping("/findMyAccountByPhoneNumber")
//    public ResponseEntity<String> findByNameAndPhoneNumber(@RequestBody findMyAccountByPhoneNumberDto dto) {
//        String email = service.findEmailByPhoneNumber(dto);
//        return ResponseEntity.ok().body(email);
//    }


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
