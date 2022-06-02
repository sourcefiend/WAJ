package hr.tvz.smolcic.hardwareapp.service;

import hr.tvz.smolcic.hardwareapp.DTOs.ReviewDTO;
import hr.tvz.smolcic.hardwareapp.interfaces.repositories.IReviewJpaRepository;
import hr.tvz.smolcic.hardwareapp.interfaces.services.IReviewService;
import hr.tvz.smolcic.hardwareapp.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewJpaRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAll() {
        return this.reviewRepository.getAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareEquals(int hardwareId) {
        return this.reviewRepository.findAllByHardware_idEquals(hardwareId).stream().filter(review -> review.getHardware() == hardwareId).map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(final Review review) {
        return new ReviewDTO(review.getText(), review.getRating());
    }
}
