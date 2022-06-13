package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
import hansung.mannayo.mannayoserverapplication.dto.MemberDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import hansung.mannayo.mannayoserverapplication.dto.ReviewRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public List<Review> findAll();

    //find review by Id
    public Optional<Review> findById(Long id);

    public List<ReviewDto> findByRestaurantId(Long id);

    public Optional<Review> findimagebyId(Long id);

    //save member
    public Review insert(ReviewRequestDto obj);

    public void updateImageAddress(Review review);

    //delete member by pk(nickname)
    public void delete(Long id);

    //update Member by pk(nickname)
    public Review update(Long id, ReviewDto obj);


    public Long getCountReviewsByRestaurantId(Long id);

    public void updateData(Review entity, ReviewDto obj);

    public Long getCountReviews();



}
