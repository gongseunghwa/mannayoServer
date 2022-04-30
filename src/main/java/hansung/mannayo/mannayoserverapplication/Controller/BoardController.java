package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Jjim;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Like;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Restaurant;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.*;
import hansung.mannayo.mannayoserverapplication.dto.BoardDto;
import hansung.mannayo.mannayoserverapplication.dto.BoardListRequest;
import hansung.mannayo.mannayoserverapplication.dto.RestaurantListRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ResponseEntity<List<BoardDto>> findBoardByType(@ApiParam(value = "타입 별 게시판 찾기", required = false) @RequestParam(required = false)BoardType boardType) {
        List<Board> boards = boardService.findBoardByType(boardType).get();
        BoardDto boardDto = new BoardDto();
        List<BoardDto> boardDtos = new ArrayList<>();

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
    public ResponseEntity<List<BoardListRequest>> findBoard(
            @ApiParam(value = "제목으로 검색",required = false) @RequestParam(required = false)String title,
            @ApiParam(value = "닉네임으로 검색",required = false) @RequestParam(required = false)String nickName)
    {

        if(title == null && nickName ==null) {
            List<Board> boardList = boardService.findAll();
            List<BoardListRequest> boardListRequest = new ArrayList<>();
            toDto(boardList, boardListRequest);
            return ResponseEntity.ok().body(boardListRequest);
        }

        if(title != null && nickName ==null){
            Optional<List<Board>> boardList = boardService.findByTitle(title);
            if(boardList.isPresent()) {
                List<Board> boards = boardList.get();
                List<BoardListRequest> boardListRequest = new ArrayList<>();
                toDto(boards, boardListRequest);
                return ResponseEntity.ok().body(boardListRequest);
            }
        }

        Optional<List<Board>> boardList = boardService.findByMember(nickName);
        if(boardList.isPresent()) {
            List<Board> boards = boardList.get();
            List<BoardListRequest> boardListRequest = new ArrayList<>();
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
    public void toDto(List<Board> boards, List<BoardListRequest> dtoList){
        for(int i=0 ;i<boards.size();i++){
            BoardListRequest list = BoardListRequest.builder()
                    .id(boards.get(i).getId())
                    .writeDate(boards.get(i).getCreatedDate())
                    .title(boards.get(i).getTitle())
                    .writer(boards.get(i).getMember().getNickName())
                    .build();
            dtoList.add(list);
        }
    }


    @ApiOperation(value = "board write", notes = "글쓰기 기능, 타입은 GOOD_RESTAURANT_BOARD, ADVERTISE_BOARD, TODAT_EAT_BOARD 만 입력")
    @PostMapping("/write")
    public void writeInsertBoard(@RequestBody BoardDto boardDto)
    {
        Board board = Board.builder()
                .member(memberService.findbyNickname(boardDto.getNickName()))
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .type(boardDto.getBoardType())
                .createdDate(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

    // 좋아요한 게시물 스크랩하기
    @ApiOperation(value = "board scrapping")
    @GetMapping("/scrappost/{id}")
    public ResponseEntity<List<BoardListRequest>> scrappingBoard(@PathVariable Long id) {
        List<Like> likeList = likeService.findListByMemberId(id).get();
        List<Board> boards = new ArrayList<>();
        if(!likeList.isEmpty()) {
            for(int i =0; i<likeList.size(); i++) {
                boards.add(boardService.findById(likeList.get(i).getBoard().getId()).get());
            }
            List<BoardListRequest> boardListRequests = new ArrayList<>();
            toDto(boards, boardListRequests);
            return ResponseEntity.ok().body(boardListRequests);
        }

        throw new EntityNotFoundException("no likes by given id");

    }

    // 찜한 가게 스크랩하기
    @ApiOperation(value = "restaurant scrapping")
    @GetMapping("/scraprestaurant/{id}")
    public ResponseEntity<List<RestaurantListRequest>> scrappingRestaurant(@PathVariable Long id) {
        List<Jjim> jjims = jjimService.findByMemberId(id).get();
        List<Restaurant> restaurants = new ArrayList<>();
        if(!jjims.isEmpty()) {
            for(int i = 0; i<jjims.size(); i++) {
                restaurants.add(restaurantService.findById(jjims.get(i).getRestaurant().getId()).get());
            }
            List<RestaurantListRequest> restaurantListRequest = new ArrayList<>();
            toRestaurantDto(restaurants, restaurantListRequest);
            return ResponseEntity.ok().body(restaurantListRequest);
        }

        throw new EntityNotFoundException("no Jjim by given id");
    }

    //RestaurantListRequest로 정보옮기기
    public void toRestaurantDto(List<Restaurant> restaurants, List<RestaurantListRequest> dtoList){
        for(int i=0 ;i<restaurants.size();i++){
            RestaurantListRequest list = RestaurantListRequest.builder()
                    .id(restaurants.get(i).getId())
                    .name(restaurants.get(i).getName())
                    .type(restaurants.get(i).getType())
                    .address(restaurants.get(i).getAddress())
                    .build();
            dtoList.add(list);
        }
    }




}
