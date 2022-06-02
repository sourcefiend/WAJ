package hr.tvz.smolcic.hardwareapp.controller;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.DTOs.ReviewDTO;
import hr.tvz.smolcic.hardwareapp.service.ReviewService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ReviewDTO> getAllReviews() { return reviewService.getAll(); };

    @GetMapping("/{hardwareId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ReviewDTO> getReviewsForHardware(@PathVariable int hardwareId) {
        return reviewService.findAllByHardwareEquals(hardwareId);
    }

}
