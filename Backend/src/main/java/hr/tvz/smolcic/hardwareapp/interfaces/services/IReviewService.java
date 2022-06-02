package hr.tvz.smolcic.hardwareapp.interfaces.services;

import hr.tvz.smolcic.hardwareapp.DTOs.ReviewDTO;

import java.util.List;

public interface IReviewService {

    public List<ReviewDTO> getAll();

    public List<ReviewDTO> findAllByHardwareEquals(int hardwareId);
}
