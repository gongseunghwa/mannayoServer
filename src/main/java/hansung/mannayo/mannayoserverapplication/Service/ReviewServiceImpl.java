package hansung.mannayo.mannayoserverapplication.Service;

import hansung.mannayo.mannayoserverapplication.Model.Entity.Review;
import hansung.mannayo.mannayoserverapplication.Repository.ReviewRepository;
import hansung.mannayo.mannayoserverapplication.exceptions.DatabaseException;
import hansung.mannayo.mannayoserverapplication.exceptions.ResourceNotFoundException;
import hansung.mannayo.mannayoserverapplication.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findbyId(Long id) {
        Optional<Review> obj = reviewRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    @Override
    public Review insert(ReviewDto obj) {
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
        entity.setTitle(obj.getTitle());
        entity.setContent(obj.getContent());
        entity.setWriteDate(obj.getWriteDate());
        entity.setImage(obj.getImage());
        entity.setStarPoint(obj.getStarPoint());
        entity.setIsDeleted(obj.getIsDeleted());
        entity.setIsDeleted(obj.getIsDeleted());
    }
}
