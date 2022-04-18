package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Service.findMyAccount;
import hansung.mannayo.mannayoserverapplication.dto.findMyAccountByNicknameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findMyAccouunt")
public class findMyAccountController {

    findMyAccount service;

    @GetMapping
    public ResponseEntity<String> findByNameAndEmail(@RequestBody findMyAccountByNicknameDto dto) {
        return ResponseEntity.ok().body(service.findEmail(dto));
    }

}
