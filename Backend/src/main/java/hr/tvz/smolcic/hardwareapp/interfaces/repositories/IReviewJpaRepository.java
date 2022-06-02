package hr.tvz.smolcic.hardwareapp.interfaces.repositories;

import hr.tvz.smolcic.hardwareapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewJpaRepository extends JpaRepository<Review, Number> {

    @Query("SELECT r FROM Review r")
    List<Review> getAll();

    List<Review> findAllByHardware_idEquals(Number hardwareId);
}
