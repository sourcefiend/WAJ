package hr.tvz.smolcic.hardwareapp.interfaces.services;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IHardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand command);

    void delete(String code);

}
