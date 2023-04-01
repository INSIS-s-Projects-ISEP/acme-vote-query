package com.isep.acme.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.CreateReviewDTO;
import com.isep.acme.model.Rating;
import com.isep.acme.model.Review;
import com.isep.acme.model.ReviewDTO;
import com.isep.acme.model.ReviewMapper;
import com.isep.acme.model.User;
import com.isep.acme.model.Vote;
import com.isep.acme.model.VoteReviewDTO;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    UserRepository uRepository;

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestService restService;

    @Override
    public Iterable<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewDTO create(final CreateReviewDTO createReviewDTO) {

        final var user = userService.getUserId(createReviewDTO.getUserID());

        if(user.isEmpty()) return null;

        Rating rating = null;
        Optional<Rating> r = ratingService.findByRate(createReviewDTO.getRating());
        if(r.isPresent()) {
            rating = r.get();
        }

        LocalDate date = LocalDate.now();

        String funfact = restService.getFunFact(date);

        if (funfact == null) return null;

        Review review = new Review(createReviewDTO.getReviewText(), date, funfact, rating, user.get());

        review = repository.save(review);

        if (review == null) return null;

        return ReviewMapper.toDto(review);
    }

    @Override
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<Review> review = this.repository.findById(reviewID);

        if (review.isEmpty()) return false;

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if (voteReviewDTO.getVote().equalsIgnoreCase("upVote")) {
            boolean added = review.get().addUpVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
    }

    @Override
    public Boolean DeleteReview(Long reviewId)  {

        Optional<Review> rev = repository.findById(reviewId);

        if (rev.isEmpty()){
            return null;
        }
        Review r = rev.get();

        if (r.getUpVote().isEmpty() && r.getDownVote().isEmpty()) {
            repository.delete(r);
            return true;
        }
        return false;
    }

    @Override
    public List<ReviewDTO> findPendingReview(){

        Optional<List<Review>> r = repository.findPendingReviews();

        if(r.isEmpty()){
            return null;
        }

        return ReviewMapper.toDtoList(r.get());
    }

    @Override
    public ReviewDTO moderateReview(Long reviewID, String approved) throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = repository.findById(reviewID);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);

        if(!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        Review review = repository.save(r.get());

        return ReviewMapper.toDto(review);
    }


    @Override
    public List<ReviewDTO> findReviewsByUser(Long userID) {

        final Optional<User> user = uRepository.findById(userID);

        if(user.isEmpty()) return null;

        Optional<List<Review>> r = repository.findByUserId(user.get());

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r.get());
    }
}