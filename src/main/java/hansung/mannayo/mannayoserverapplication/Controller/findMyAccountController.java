package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Service.findMyAccount;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/findMyAccouunt")
public class findMyAccountController {

    findMyAccount service;

    @PostMapping
    public ResponseEntity<String> findByNameAndEmail(@RequestBody findMyAccountByNicknameDto dto) {
        System.out.println("닉네임과 실명은 " + dto.getRealName() + dto.getNickName());
        String email = service.findEmail(dto);
        return ResponseEntity.ok().body(email);
    }

}
