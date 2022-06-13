package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Member;
import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Repository.MemberRepository;
import hansung.mannayo.mannayoserverapplication.Repository.RestaurantRepository;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
import hansung.mannayo.mannayoserverapplication.dto.ReviewRequestDto;
import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Long getCountReviewsByRestaurantId(Long id) {
        return reviewRepository.countReviewsByRestaurantId(id);
    }

    @Override
    public Long getCountReviews() {
        return reviewRepository.countReview();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<ReviewDto> findByRestaurantId(Long id) {
        List<Review> reviews = reviewRepository.findByRestaurantIdOrderByWriteDateDesc(id).get();
        List<ReviewDto> obj = new ArrayList<>();
        for(Review review : reviews) {
            ReviewDto reviewDto = ReviewDto.builder()
                    .id(review.getId())
                    .memberId(review.getMember().getId())
                    .content(review.getContent())
                    .writeDate(review.getWriteDate())
                    .image(review.getImage())
                    .starPoint(review.getStarPoint())
                    .isModifoed(review.getIsModified())
                    .isDeleted(review.getIsDeleted())
                    .memberImage(review.getMember().getImageAddress())
                    .memberNickname(review.getMember().getNickName())
                    .build();
            obj.add(reviewDto);
        }
        return obj;
    }

    public Optional<Review> findimagebyId(Long id) {
        Optional<Review> obj = reviewRepository.findById(id);
        return obj;
    }

    @Override
    public void updateImageAddress(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Review insert(ReviewRequestDto obj) {
        Review review = dtoToEntity(obj);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Long id) {
        try{
            reviewRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Review update(Long id, ReviewDto obj) {
        try{
            Review entity = reviewRepository.getById(id);
            updateData(entity,obj);
            return reviewRepository.save(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public void updateData(Review entity, ReviewDto obj) {
        entity.setContent(obj.getContent());
        entity.setWriteDate(obj.getWriteDate());
        entity.setImage(obj.getImage());
        entity.setStarPoint(obj.getStarPoint());
        entity.setIsDeleted(obj.getIsDeleted());
        entity.setIsDeleted(obj.getIsDeleted());
    }

    private Review dtoToEntity(ReviewRequestDto reviewRequestDto) {
        Review entity = Review.builder()
                .content(reviewRequestDto.getContent())
                .member(memberRepository.findById(reviewRequestDto.getMemberId()).get())
                .restaurant(restaurantRepository.findById(reviewRequestDto.getRestaurantId()).get())
                .starPoint(reviewRequestDto.getStarPoint())
                .build();
        return entity;
    }
}
