package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Service.*;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.JjimDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jjim")
public class JjimController {

    @Autowired
    JjimService jjimService;

    @Autowired
    MemberService memberService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ResponseService responseService;

    @ApiOperation("가게 찜")
    @PostMapping
    public ResponseEntity<CommonResult> jjimUp(@RequestParam Long memberid, @RequestParam Long restaurantid) {

        Member member = memberService.findbyId(memberid);
        Restaurant restaurant = restaurantService.findbyId(restaurantid).get();
        CommonResult commonResult = new CommonResult();

        Jjim jjim = jjimService.insertJjim(member, restaurant);

        if(jjim != null) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }
        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

    @ApiOperation("가게 찜 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<CommonResult> jjimDown(@RequestParam Long memberid, @RequestParam Long restaurantid) {

        CommonResult commonResult = new CommonResult();

        if(jjimService.deleteJjim(memberid, restaurantid)) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }

        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);

    }
}
