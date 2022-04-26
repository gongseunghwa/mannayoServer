package hansung.mannayo.mannayoserverapplication.Controller;


import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Service.BoardService;
import hansung.mannayo.mannayoserverapplication.dto.BoardListRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    MemberRepository memberRepository;

    //게시글list 모두 불러오기
    //제목으로 검색
    //닉네임으로 검색
    // RequestParam의 인자에 따라 결과값 다르게 출력
    @ApiOperation(value = "board", notes = "전체검색/닉네임검색/제목검색")
    @GetMapping("/board")
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
            List<Board> boardList = boardService.findByTitle(title);
            List<BoardListRequest> boardListRequest = new ArrayList<>();
            toDto(boardList,boardListRequest);
            return ResponseEntity.ok().body(boardListRequest);
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

}
