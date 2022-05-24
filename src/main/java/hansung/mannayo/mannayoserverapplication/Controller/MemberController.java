package hansung.mannayo.mannayoserverapplication.Controller;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Block;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Service.BlockService;
import hansung.mannayo.mannayoserverapplication.Service.MemberService;
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
import org.apache.commons.collections.ArrayStack;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    BlockService blockService;

    private final ResponseService responseService;

    String AWSfilepath = "/home/ec2-user/images/";

    String localfilepath = "C://images/profile/";

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 제목과 설명 표시
    @GetMapping
    public ResponseEntity<ListResult<Member>> findAll(){
        List<Member> list = memberService.findAll();
        return ResponseEntity.ok().body(responseService.getListResult(list));
    }

    @ApiOperation(value = "닉네임으로 회원 찾기")
    @GetMapping("/{id}")
    public ResponseEntity<Member> findbyNickName(@PathVariable Long id){
        Member obj = memberService.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "닉네임으로 계정 찾기")
    @PostMapping("/findMyAccountByNickname")
    public ResponseEntity<findMyAccountByNicknameResponseDto> findByNameAndNickname(@RequestBody findMyAccountByNicknameDto dto) {
        System.out.println(dto);
        findMyAccountByNicknameResponseDto responsedto = new findMyAccountByNicknameResponseDto();
        responsedto.setEmail(memberService.findEmailByNickname(dto));
        System.out.println(responsedto.getEmail());
        return ResponseEntity.ok().body(responsedto);
    }

    @ApiOperation(value = "휴대폰번호로 계정찾기")
    @PostMapping("/findMyAccountByPhoneNumber")
    public ResponseEntity<findMyAccountByPhoneNumberResponseDto> findByNameAndPhoneNumber(@RequestBody findMyAccountByPhoneNumberDto dto) {
        System.out.println(dto);
        findMyAccountByPhoneNumberResponseDto responsedto = new findMyAccountByPhoneNumberResponseDto();
        responsedto.setEmail(memberService.findEmailByPhoneNumber(dto));
        System.out.println(responsedto.getEmail());
        return ResponseEntity.ok().body(responsedto);
    }

    @ApiOperation(value = "이메일로 잊어버린 비밀번호 변경하기")
    @PostMapping("/findMyPasswordByEmail")
    public ResponseEntity findByPasswordByEmail(@RequestBody findMyPasswordByEmailDto dto) {
        boolean isExist = memberService.findPasswordByEmail(dto);
        return ResponseEntity.ok().body(isExist);
    }

    @ApiOperation(value = "휴대폰 번호로 잊어버린 비밀번호 변경하기")
    @PostMapping("/findMyPasswordByPhoneNumber")
    public ResponseEntity findByPasswordByPhoneNumber(@RequestBody findMyPasswordByPhoneNumberDto dto) {
        boolean isExist = memberService.findPasswordByPhoneNumber(dto);
        return ResponseEntity.ok().body(isExist);
    }


    @ApiOperation(value = "계정삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "계정 수정하기")
    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberDto obj){
        return ResponseEntity.ok().body(memberService.update(id,obj));
    }

    @ApiOperation(value = "차단하기")
    @PostMapping("/block")
    public void blockMember(@RequestBody BlockDto blockDto) {

        Member member = memberService.findbyId(blockDto.getMemberId());
        Member blockMember = memberService.findbyId(blockDto.getBlockedMemberId());

        blockService.insert(member, blockMember);

    }

    @ApiOperation(value = "차단내역 불러오기")
    @GetMapping("/block")
    public ResponseEntity<List<BlockResponseDto>> showBlockMember(@RequestParam Long id) {

        List<Block> blocks = blockService.findByMemberId(id).get();
        List<BlockResponseDto> blockResponseDtos = new ArrayList<>();
        BlockResponseDto blockResponseDto = new BlockResponseDto();

        for(int i=0; i< blocks.size();i++) {
            blockResponseDto.setMemberId(blocks.get(i).getTarget_member().getId());
            blockResponseDto.setNickname(blocks.get(i).getTarget_member().getNickName());
            blockResponseDtos.add(blockResponseDto);
        }

        return ResponseEntity.ok().body(blockResponseDtos);
    }

    @ApiOperation(value = "닉네임 입력하기")
    @PostMapping("/inputnickname")
    public ResponseEntity<CommonResult> inputNickname(@RequestParam Long id, @RequestParam String nickname) {
        CommonResult commonResult = new CommonResult();
        if(memberService.updateNickname(id, nickname)) {
            commonResult = responseService.getSuccessResult();

            return ResponseEntity.ok().body(commonResult);
        }
        commonResult = responseService.getFailResult();
        return ResponseEntity.ok().body(commonResult);
    }

    @ApiOperation(value = "프로필 사진 등록")
    @PostMapping("/profileimage")
    public ResponseEntity<CommonResult> registerProfileImage(@RequestParam Long id, @RequestPart MultipartFile multipartFile) {
        Date date = new Date(); // 파일명이 겹치는것을 방지 하기 위해 파일명에 시간변수를 추가
        StringBuilder sb = new StringBuilder(); // 파일명 스트링 빌더
        Member member = new Member(); // 멤버객체 생성
        CommonResult commonResult = new CommonResult();
        if(multipartFile.isEmpty()) { // request된 파일의 존재여부 확인
            sb.append("none");
            commonResult = responseService.getFailResult();
        } else {
            sb.append(date.getTime());
            sb.append(multipartFile.getOriginalFilename());
        }

        if(!multipartFile.isEmpty()) { // request된 파일이 존재한다면

            File dest = new File(localfilepath + sb.toString()); // 파일 생성
            try {
                member = memberService.findbyId(id); // id로 Entity 찾아옴
                if(member.getImageAddress() == null) { // 이미 이미지 주소가 없다면 (기존에 프로필을 올린적이 없다면)
                    member.setImageAddress(localfilepath + sb.toString()); // member Entity에 이미지주소 저장
                    memberService.updateImageAddress(member); // 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                    System.out.println("파일 저장 완료 1");
                }else {
                    File file = new File(member.getImageAddress()); // 기존에 저장된 파일 경로 DB에서 가져온 후 파일 인스턴스 생성
                    if(file.exists()) { // file이 존재한다면
                        file.delete(); // 삭제
                    }

                    member.setImageAddress(localfilepath + sb.toString()); // 새로운 이미지 주소 DB에 저장
                    memberService.updateImageAddress(member); // Entity 업데이트
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

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "profileimage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("id") Long id) throws IOException {
        Member member = memberService.findbyId(id);
        String imagename = member.getImageAddress();
        InputStream imageStream = new FileInputStream(imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }





}
