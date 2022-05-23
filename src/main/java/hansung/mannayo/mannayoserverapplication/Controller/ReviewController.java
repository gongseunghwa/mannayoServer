package hansung.mannayo.mannayoserverapplication.Controller;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Service.ResponseService;
import hansung.mannayo.mannayoserverapplication.Service.ReviewService;
import hansung.mannayo.mannayoserverapplication.dto.CommonResult;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewRequestDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ResponseService responseService;

    String AWSfilepath = "/home/ec2-user/images/";

    String localfilepath = "C://images/profile/";

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
    public ResponseEntity<List<ReviewDto>> findbyRestaurantId(@PathVariable Long id){ //restaurant id
        List<ReviewDto> obj = reviewService.findByRestaurantId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CommonResult> insert(@RequestBody ReviewRequestDto reviewRequestDto){
        CommonResult commonResult = new CommonResult();
        Review review = reviewService.insert(reviewRequestDto);

        if(review != null) {
            commonResult = responseService.getSuccessResult();
            commonResult.setMsg(review.getId().toString());
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
        Optional<Review> review = reviewService.findimagebyId(id);
        String imagename = review.get().getImage();
        InputStream imageStream = new FileInputStream(imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    @ApiOperation(value = "리뷰 사진 등록")
    @PostMapping("/reviewimage")
    public ResponseEntity<CommonResult> registerProfileImage(@RequestParam Long id, @RequestPart MultipartFile multipartFile) {
        Date date = new Date(); // 파일명이 겹치는것을 방지 하기 위해 파일명에 시간변수를 추가
        StringBuilder sb = new StringBuilder(); // 파일명 스트링 빌더
        Review review = new Review(); // 멤버객체 생성
        CommonResult commonResult = new CommonResult();
        if(multipartFile.isEmpty()) { // request된 파일의 존재여부 확인
            sb.append("none");
            commonResult = responseService.getFailResult();
        } else {
            sb.append(date.getTime());
            sb.append(multipartFile.getOriginalFilename());
        }

        if(!multipartFile.isEmpty()) { // request된 파일이 존재한다면

            File dest = new File(AWSfilepath + sb.toString()); // 파일 생성
            try {
                review = reviewService.findById(id).get();// id로 Entity 찾아옴
                if(review.getImage() == null) { // 이미 이미지 주소가 없다면 (기존에 프로필을 올린적이 없다면)
                    review.setImage(AWSfilepath + sb.toString()); // member Entity에 이미지주소 저장
                    reviewService.updateImageAddress(review); // 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                    System.out.println("파일 저장 완료 1");
                }else {
                    File file = new File(review.getImage()); // 기존에 저장된 파일 경로 DB에서 가져온 후 파일 인스턴스 생성
                    if(file.exists()) { // file이 존재한다면
                        file.delete(); // 삭제
                    }

                    review.setImage(AWSfilepath + sb.toString()); // 새로운 이미지 주소 DB에 저장
                    reviewService.updateImageAddress(review); // Entity 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                    System.out.println("파일 저장 완료 2");
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        commonResult = responseService.getSuccessResult();
        return ResponseEntity.ok().body(commonResult);
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
                    .writeDate(r.getWriteDate())
                    .memberImage(r.getMember().getImageAddress())
                    .memberNickname(r.getMember().getNickName())
                    .build();
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
}
