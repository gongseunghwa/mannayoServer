package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Board;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Comment;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Repository.BoardRepository;
import hansung.mannayo.mannayoserverapplication.Repository.CommentRepository;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Long getCountCommentByBoardId(Long id) {
        return commentRepository.countByBoardId(id);
    }


    @Override
    public Optional<List<Comment>> getCommentByBoardId(Long id) {
        return commentRepository.findByBoardId(id);
    }

    @Override
    public Boolean setComment(Long memberid, Long boardid, String contents) {
        Optional<Member> member = memberRepository.findById(memberid);
        Optional<Board> board = boardRepository.findById(boardid);
        Comment comment = new Comment();
        if(member.isPresent() && board.isPresent()) {
            comment = Comment.builder()
                    .Depth(1)
                    .contents(contents)
                    .nickName(member.get().getNickName())
                    .board(board.get())
                    .build();
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }
}
