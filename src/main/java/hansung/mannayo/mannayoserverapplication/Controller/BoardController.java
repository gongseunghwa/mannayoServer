package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.*;

import hansung.mannayo.mannayoserverapplication.dto.BoardDetailResponse;
import hansung.mannayo.mannayoserverapplication.dto.BoardListResponse;
import hansung.mannayo.mannayoserverapplication.dto.BoardWriteDto;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantListResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    @Autowired
    LikeService likeService;

    @Autowired
    JjimService jjimService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;


    @ApiOperation(value = "response board", notes = "게시판 타입 별 호출")
    @GetMapping
    public ResponseEntity<List<BoardDetailResponse>> findBoardByType(@ApiParam(value = "타입 별 게시판 찾기", required = false) @RequestParam(required = false)BoardType boardType) {
        List<Board> boards = boardService.findBoardByType(boardType).get();
        BoardDetailResponse boardDto = new BoardDetailResponse();
        List<BoardDetailResponse> boardDtos = new ArrayList<>();

        for(int i = 0; i<boards.size(); i++) {
            boardDto.setBoardId(boards.get(i).getId());
            boardDto.setBoardType(boards.get(i).getType());
            boardDto.setContents(boards.get(i).getContents());
            boardDto.setDate(boards.get(i).getCreatedDate());
            boardDto.setImage(boards.get(i).getImage());
            boardDto.setMemberId(boards.get(i).getMember().getId());
            boardDto.setNickName(boards.get(i).getMember().getNickName());
            boardDto.setTitle(boards.get(i).getTitle());
            boardDtos.add(boardDto);
        }

        return ResponseEntity.ok().body(boardDtos);
    }
    //게시글list 모두 불러오기
    //제목으로 검색
    //닉네임으로 검색
    //RequestParam의 인자에 따라 결과값 다르게 출력
    @ApiOperation(value = "board", notes = "전체검색/닉네임검색/제목검색")
    @GetMapping("/search")
    public ResponseEntity<List<BoardListResponse>> findBoard(
            @ApiParam(value = "제목으로 검색",required = false) @RequestParam(required = false)String title,
            @ApiParam(value = "닉네임으로 검색",required = false) @RequestParam(required = false)String nickName)
    {

        if(title == null && nickName ==null) {
            List<Board> boardList = boardService.findAll();
            List<BoardListResponse> boardListRequest = new ArrayList<>();
            toDto(boardList, boardListRequest);
            return ResponseEntity.ok().body(boardListRequest);
        }

        if(title != null && nickName ==null){
            Optional<List<Board>> boardList = boardService.findByTitle(title);
            if(boardList.isPresent()) {
                List<Board> boards = boardList.get();
                List<BoardListResponse> boardListRequest = new ArrayList<>();
                toDto(boards, boardListRequest);
                return ResponseEntity.ok().body(boardListRequest);
            }
        }

        Optional<List<Board>> boardList = boardService.findByMember(nickName);
        if(boardList.isPresent()) {
            List<Board> boards = boardList.get();
            List<BoardListResponse> boardListRequest = new ArrayList<>();
            toDto(boards, boardListRequest);
            return ResponseEntity.ok().body(boardListRequest);
        }

        throw new EntityNotFoundException("No Boards by given nickName");
    }


    //게시글 상세정보 불러오기


//    //제목으로 검색하기
//    @GetMapping("/title/{title}")
//    public ResponseEntity<List<BoardListRequest>> findByTitle(@PathVariable String title){
//        List<Board> boardList = boardService.findByTitle(title);
//        List<BoardListRequest> boardListRequest = new ArrayList<>();
//        toDto(boardList,boardListRequest);
//        return ResponseEntity.ok().body(boardListRequest);
//    }
//
//    //작성자로 검색하기
//    @GetMapping("/member/{nickName}")
//    public ResponseEntity<List<BoardListRequest>> findByWriter(@PathVariable String nickName){
//        Optional<List<Board>> boardList = boardService.findByMember(nickName);
//        if(boardList.isPresent()) {
//            List<Board> boards = boardList.get();
//            List<BoardListRequest> boardListRequest = new ArrayList<>();
//            toDto(boards, boardListRequest);
//            return ResponseEntity.ok().body(boardListRequest);
//        }
//
//        throw new EntityNotFoundException("No Boards by given nickName");
//    }


    //BoardListRequest로 정보옮기기
    public void toDto(List<Board> boards, List<BoardListResponse> dtoList){
        for(int i=0 ;i<boards.size();i++){
            BoardListResponse list = BoardListResponse.builder()
                    .id(boards.get(i).getId())
                    .writeDate(boards.get(i).getCreatedDate())
                    .title(boards.get(i).getTitle())
                    .writer(boards.get(i).getMember().getNickName())
                    .build();
            dtoList.add(list);
        }
    }

    // 게시판 글쓰기 기능
    @ApiOperation(value = "board write", notes = "글쓰기 기능, 타입은 GOOD_RESTAURANT_BOARD, ADVERTISE_BOARD, TODAT_EAT_BOARD 만 입력")
    @PostMapping(value = "/write")
    public void writeInsertBoard(@RequestPart(value = "file") MultipartFile multipartFile,
                                 @RequestPart(value = "request", required = false) BoardWriteDto boardWriteDto)

    {
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        Board board = Board.builder()
                .member(memberService.findbyNickname(boardWriteDto.getNickName()))
                .title(boardWriteDto.getTitle())
                .contents(boardWriteDto.getContents())
                .type(boardWriteDto.getBoardType())
                .createdDate(LocalDateTime.now())
                .build();
        boardRepository.save(board);

        if(multipartFile.isEmpty()) { // request된 파일의 존재여부 확인
            sb.append("none");
        } else {
            sb.append(date.getTime());
            sb.append(multipartFile.getOriginalFilename());
        }

        if(!multipartFile.isEmpty()) { // request된 파일이 존재한다면
            File dest = new File("C://images/board/" + sb.toString()); // 파일 생성
            try {
                board = boardService.findById(board.getId()).get(); // id로 Entity 찾아옴
                if(board.getImage() == null) { // 이미 이미지 주소가 없다면 (기존에 프로필을 올린적이 없다면)
                    board.setImage("C://images/board/" + sb.toString()); // member Entity에 이미지주소 저장
                    boardService.updateImageAddress(board); // 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                }else {
                    File file = new File(board.getImage()); // 기존에 저장된 파일 경로 DB에서 가져온 후 파일 인스턴스 생성
                    if(file.exists()) { // file이 존재한다면
                        file.delete(); // 삭제
                    }
                    board.setImage("C://images/board/" + sb.toString()); // 새로운 이미지 주소 DB에 저장
                    boardService.updateImageAddress(board); // Entity 업데이트
                    multipartFile.transferTo(dest); // 파일 저장
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    // 좋아요한 게시물 스크랩하기
    @ApiOperation(value = "board scrapping")
    @GetMapping("/scrappost/{id}")
    public ResponseEntity<List<BoardListResponse>> scrappingBoard(@PathVariable Long id) {
        List<Like> likeList = likeService.findListByMemberId(id).get();
        List<Board> boards = new ArrayList<>();
        if(!likeList.isEmpty()) {
            for(int i =0; i<likeList.size(); i++) {
                boards.add(boardService.findById(likeList.get(i).getBoard().getId()).get());
            }
            List<BoardListResponse> boardListRequests = new ArrayList<>();
            toDto(boards, boardListRequests);
            return ResponseEntity.ok().body(boardListRequests);
        }

        throw new EntityNotFoundException("no likes by given id");

    }

    // 찜한 가게 스크랩하기
    @ApiOperation(value = "restaurant scrapping")
    @GetMapping("/scraprestaurant/{id}")
    public ResponseEntity<List<RestaurantListResponse>> scrappingRestaurant(@PathVariable Long id) {
        List<Jjim> jjims = jjimService.findByMemberId(id).get();
        List<Restaurant> restaurants = new ArrayList<>();
        if(!jjims.isEmpty()) {
            for(int i = 0; i<jjims.size(); i++) {
                restaurants.add(restaurantService.findbyId(jjims.get(i).getRestaurant().getId()).get());
            }
            List<RestaurantListResponse> restaurantListRequest = new ArrayList<>();
            toRestaurantDto(restaurants, restaurantListRequest);
            return ResponseEntity.ok().body(restaurantListRequest);
        }

        throw new EntityNotFoundException("no Jjim by given id");
    }

    //RestaurantListRequest로 정보옮기기
    public void toRestaurantDto(List<Restaurant> restaurants, List<RestaurantListResponse> dtoList){
        for(int i=0 ;i<restaurants.size();i++){
            RestaurantListResponse list = RestaurantListResponse.builder()
                    .id(restaurants.get(i).getId())
                    .name(restaurants.get(i).getName())
                    .type(restaurants.get(i).getType())
                    .address(restaurants.get(i).getAddress())
                    .build();
            dtoList.add(list);
        }
    }




}
