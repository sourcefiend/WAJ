package hr.tvz.smolcic.hardwareapp.interfaces.repositories;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import hr.tvz.smolcic.hardwareapp.model.Hardware;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface IHardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    void delete(String code);

}
