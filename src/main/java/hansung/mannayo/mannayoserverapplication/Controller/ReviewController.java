package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.Service.ReviewService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ResponseService responseService;

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
    public ResponseEntity<Review> findbyId(@PathVariable Long id){ //restaurant id
        Review obj = reviewService.findbyId(id).get();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CommonResult> insert(@RequestBody ReviewDto reviewDto){
        CommonResult commonResult = new CommonResult();
        if(reviewService.insert(reviewDto) != null) {
            commonResult = responseService.getSuccessResult();
            return ResponseEntity.ok().body(commonResult);
        }

        commonResult = responseService.getFailResult();
        return  ResponseEntity.ok().body(commonResult);
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

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("id") Long id) throws IOException {
        Optional<Review> review = reviewService.findbyId(id);
        String imagename = review.get().getImage();
        InputStream imageStream = new FileInputStream(imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    private List<ReviewDto> toConvertDto(List<Review> list) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(Review r : list) {
            ReviewDto reviewDto= ReviewDto.builder()
                    .content(r.getContent())
                    .memberId(r.getMember().getId())
                    .image(r.getImage())
                    .isDeleted(r.getIsDeleted())
                    .isModifoed(r.getIsModified())
                    .starPoint(r.getStarPoint())
                    .title(r.getTitle())
                    .writeDate(r.getWriteDate())
                    .memberImage(r.getMember().getImageAddress())
                    .memberNickname(r.getMember().getNickName())
                    .build();
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
}
