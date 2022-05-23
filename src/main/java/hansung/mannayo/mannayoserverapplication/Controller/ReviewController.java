package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Service.ReviewService;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> findAll(){
        List<Review> list = reviewService.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        return ResponseEntity.ok().body(reviewDtoList);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 때 받은 토큰",required = true,dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "id로 리뷰조회(1개)" ,notes = "Id로 리뷰를 조회한다")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Review> findbyId(@PathVariable Long id){
        Review obj = reviewService.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Review> insert(@RequestBody ReviewDto reviewDto){
        return  ResponseEntity.ok(reviewService.insert(reviewDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody ReviewDto obj){
        return ResponseEntity.ok().body(reviewService.update(id,obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Review> delete(@PathVariable Long id, @RequestBody ReviewDto obj){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private List<ReviewDto> toConvertDto(List<Review> list) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(Review r : list) {
            ReviewDto reviewDto= ReviewDto.builder()
                    .content(r.getContent())
                    .id(r.getId())
                    .memberId(r.getMember().getId())
                    .image(r.getImage())
                    .isDeleted(r.getIsDeleted())
                    .isModifoed(r.getIsModified())
                    .starPoint(r.getStarPoint())
                    .title(r.getTitle())
                    .writeDate(r.getWriteDate())
                    .memberImage(r.getMember().getImageAddress())
                    .build();
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
}
