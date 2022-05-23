package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {
    public List<Review> findAll();

    //find member by pk(nickname)
    public Review findbyId(Long id);

    //save member
    public Review insert(ReviewDto obj);

    //delete member by pk(nickname)
    public void delete(Long id);

    //update Member by pk(nickname)
    public Review update(Long id, ReviewDto obj);


    public void updateData(Review entity, ReviewDto obj);



}
