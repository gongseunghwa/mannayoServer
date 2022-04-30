package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Service.JjimService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
import hansung.mannayo.mannayoserverapplication.Service.MemberServiceImpl;
import hansung.mannayo.mannayoserverapplication.Service.RestaurantService;
import hansung.mannayo.mannayoserverapplication.dto.JjimDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("가게 찜")
    @PostMapping
    public void jjimUp(@RequestBody JjimDto jjimDto) {

        Member member = memberService.findbyId(jjimDto.getMemberid());
        Restaurant restaurant = restaurantService.findById(jjimDto.getRestaurantid()).get();

        jjimService.insertJjim(member, restaurant);

    }
}
