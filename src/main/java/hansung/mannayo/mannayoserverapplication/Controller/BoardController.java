package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.*;
import hansung.mannayo.mannayoserverapplication.Model.Type.BoardType;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.*;

import hansung.mannayo.mannayoserverapplication.dto.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final ResponseService responseService;

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

    @Autowired
    CommentService commentService;

    @Autowired
    CommentToCommentService commentToCommentService;


    String AWSfilepath = "/home/ec2-user/images/";

    String localfilepath = "C://images/board/";


    @ApiOperation(value = "response board", notes = "게시판 타입 별 호출")
    @GetMapping
    public ResponseEntity<List<BoardDetailResponse>> findBoardByType(@ApiParam(value = "타입 별 게시판 찾기", required = false) @RequestParam(required = false) BoardType boardType) {
        List<Board> boards = boardService.findBoardByType(boardType).get();
        List<BoardDetailResponse> boardDtos = new ArrayList<>();
        Long TotalCommentCount = 0L;
        Long TotalLikeCount = 0L;


        for (Board board : boards) {
            List<Comment> comments = commentService.getCommentByBoardId(board.getId()).get();
            TotalCommentCount += commentService.getCountCommentByBoardId(board.getId());
            TotalLikeCount += likeService.getCountLike(board.getId());
            Member member = memberService.findbyId(board.getMember().getId());

            for (Comment comment : comments) {
                TotalCommentCount += commentToCommentService.countByCommentId(comment.getId());
            }

            BoardDetailResponse boardDetailResponse = BoardDetailResponse.builder()
                    .boardId(board.getId())
                    .boardType(board.getType())
                    .contents(board.getContents())
                    .date(board.getCreatedDate())
                    .image(board.getImage())
                    .memberId(board.getMember().getId())
                    .nickName(board.getMember().getNickName())
                    .likeCount(TotalLikeCount)
                    .commentCount(TotalCommentCount)
                    .isVote(board.getIsVote())
                    .build();

            boardDtos.add(boardDetailResponse);
        }


        System.out.println(boardDtos);
        return ResponseEntity.ok().body(boardDtos);
    }

    @ApiOperation(value = "response board by Id", notes = "게시판 id로 호출")
    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponse> findBoardById(@ApiParam(value = "Id 별 게시판 찾기", required = false) @PathVariable(required = false) Long id) {
        Board board = boardService.findById(id).get();
        BoardDetailResponse boardDto = new BoardDetailResponse();
        Long TotalCommentCount = 0L;
        Long TotalLikeCount = 0L;


        Optional<List<Comment>> comments = commentService.getCommentByBoardId(board.getId());
        TotalCommentCount += commentService.getCountCommentByBoardId(board.getId());
        TotalLikeCount += likeService.getCountLike(board.getId());
        Optional<String> image = memberService.getImageAddress(board.getMember().getId());
        if(image.isPresent()) {
            boardDto.setIsProfile(true);
        }else {
            boardDto.setIsProfile(false);
        }
        if(comments.isPresent()) {
            List<Comment> commentList = comments.get();
            for (Comment comment : commentList) {
                TotalCommentCount += commentToCommentService.countByCommentId(comment.getId());
            }
        }

        boardDto.setBoardId(board.getId());
        boardDto.setBoardType(board.getType());
        boardDto.setContents(board.getContents());
        boardDto.setDate(board.getCreatedDate());
        boardDto.setImage(board.getImage());
        boardDto.setMemberId(board.getMember().getId());
        boardDto.setNickName(board.getMember().getNickName());
        boardDto.setLikeCount(TotalLikeCount);
        boardDto.setCommentCount(TotalCommentCount);
        boardDto.setIsVote(board.getIsVote());

        return ResponseEntity.ok().body(boardDto);
    }

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
    public void toDto(List<Board> boards, List<BoardListResponse> dtoList) {
        for (int i = 0; i < boards.size(); i++) {
            BoardListResponse list = BoardListResponse.builder()
                    .id(boards.get(i).getId())
                    .writeDate(boards.get(i).getCreatedDate())
                    .writer(boards.get(i).getMember().getNickName())
                    .build();
            dtoList.add(list);
        }
    }


    // "/board"로 post 요청이 올 시 동작
    //게시판 글쓰기기능>>

    @ApiOperation(value = "게시판 글쓰기 기능", notes = "게시판을 작성할 떄 동작 (이미지는 다른 컨트롤러에서 따로 처리한다)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 때 받은 토큰", required = false, dataType = "String", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<CommonResult> insert(@RequestBody BoardRequest dto) {
        CommonResult commonResult = new CommonResult();
        Board board = boardService.insert(dto);

        if (board != null) {
            commonResult = responseService.getSuccessResult();
            commonResult.setMsg(board.getId().toString());
            return ResponseEntity.ok(commonResult);
        }

        commonResult = responseService.getFailResult();
        return ResponseEntity.ok(commonResult);
    }

    //게시판 이미지 등록기능>>

    @ApiOperation(value = "게시판 이미지 등록", notes = "게시판을 작성할 떄 동작")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 때 받은 토큰", required = false, dataType = "String", paramType = "header")
    })
    @PostMapping("/boardimages")
    public ResponseEntity<CommonResult> uploadBoardImage(@RequestParam Long id, @RequestPart MultipartFile multipartFile) {
        Date date = new Date(); //파일명 겹치기 방지
        StringBuilder sb = new StringBuilder();
        Board board;
        CommonResult commonResult;

        if (multipartFile.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(date.getTime());
            sb.append(multipartFile.getOriginalFilename());

            File dest = new File(localfilepath + sb.toString());
            try {
                board = boardService.findById(id).get(); //id로 이미지 주소를 저장할 board 찾아오기
                board.setImage(dest.getPath());
                boardService.updateImageAddress(board); //주소를 업데이트 후 저장
                multipartFile.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
//                commonResult = responseService.getFailResult();
//                return ResponseEntity.ok(commonResult);
            } catch (IOException e) {
                e.printStackTrace();
//                commonResult = responseService.getFailResult();
//                return ResponseEntity.ok(commonResult);
            }


        }

        commonResult = responseService.getSuccessResult();
        return ResponseEntity.ok(commonResult);
    }


    // 좋아요한 게시물 스크랩하기
    @ApiOperation(value = "board scrapping")
    @GetMapping("/scrappost/{id}")
    public ResponseEntity<List<BoardListResponse>> scrappingBoard(@PathVariable Long id) {
        List<Like> likeList = likeService.findListByMemberId(id).get();
        List<Board> boards = new ArrayList<>();
        if (!likeList.isEmpty()) {
            for (int i = 0; i < likeList.size(); i++) {
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
        if (!jjims.isEmpty()) {
            for (int i = 0; i < jjims.size(); i++) {
                restaurants.add(restaurantService.findbyId(jjims.get(i).getRestaurant().getId()).get());
            }
            List<RestaurantListResponse> restaurantListRequest = new ArrayList<>();
            toRestaurantDto(restaurants, restaurantListRequest);
            return ResponseEntity.ok().body(restaurantListRequest);
        }

        throw new EntityNotFoundException("no Jjim by given id");
    }

    //RestaurantListRequest로 정보옮기기
    public void toRestaurantDto(List<Restaurant> restaurants, List<RestaurantListResponse> dtoList) {
        for (int i = 0; i < restaurants.size(); i++) {
            RestaurantListResponse list = RestaurantListResponse.builder()
                    .id(restaurants.get(i).getId())
                    .name(restaurants.get(i).getName())
                    .type(restaurants.get(i).getType())
                    .address(restaurants.get(i).getAddress())
                    .build();
            dtoList.add(list);
        }
    }

    @ApiOperation(value = "feed image 조회 ", notes = "feed Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
    @GetMapping(value = "/image/{boardId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getBoardImage(@PathVariable("boardId") Long boardId) throws IOException {
        Board board = boardService.findById(boardId).get();
        String imagename = board.getImage();
        InputStream imageStream = new FileInputStream(imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }



}
