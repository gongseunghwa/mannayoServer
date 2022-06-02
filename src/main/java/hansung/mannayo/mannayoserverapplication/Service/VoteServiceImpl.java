package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Vote;
import hansung.mannayo.mannayoserverapplication.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    BoardService boardService;

    @Override
    public Optional<List<Vote>> getVoteByBoardId(Long id) {
        return voteRepository.findByBoardId(id);
    }

    @Override
    public Vote insertVote(Long id , String contents) {
        Vote obj = Vote.builder()
                .board(boardService.findById(id).get())
                .Contents(contents)
                .build();

        return voteRepository.save(obj);
    }

    @Override
    public Vote findById(Long id) {
        return voteRepository.findById(id).get();
    }
}
