package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping
    public ResponseEntity<List<Member>> findAll(){
        List<Member> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{nickName}")
    public ResponseEntity<Member> findbyNickName(@PathVariable String nickName){
        Member obj = service.findbyNickName(nickName);
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Member> insert(Member obj){
        obj = service.insert(obj);
        System.out.println(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{nickName}}")
                .buildAndExpand(obj.getNickName()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{nickName}")
    public ResponseEntity<Void> delete(@PathVariable String nickName){
        service.delete(nickName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{nickName}")
    public ResponseEntity<Member> update(@PathVariable String nickName, @RequestBody Member obj){
        obj = service.update(nickName,obj);
        return ResponseEntity.ok().body(obj);
    }


}
