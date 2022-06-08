package hr.tvz.smolcic.hardwareapp.service;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import hr.tvz.smolcic.hardwareapp.interfaces.services.IHardwareService;
import hr.tvz.smolcic.hardwareapp.model.Hardware;
import hr.tvz.smolcic.hardwareapp.repository.JdbcMockHardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareService implements IHardwareService {

    private final JdbcMockHardwareRepository hardwareRepository;

    public HardwareService(JdbcMockHardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return this.hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return this.hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {
        return this.hardwareRepository.save(mapHardwareCommandToHardware(command)).map(hw -> new HardwareDTO(hw.getName(), hw.getPrice(), hw.getCode(), hw.getStock()));
    }

    @Override
    public void delete(String code) {
        this.hardwareRepository.delete(code);
    }


    private HardwareDTO mapHardwareToDTO(final Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getPrice(), hardware.getCode(), hardware.getStock());
    }

    private Hardware mapHardwareCommandToHardware(final HardwareCommand hardwareCommand) {
        return new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(), hardwareCommand.getPrice(), HardwareType.valueOf(hardwareCommand.getType()), hardwareCommand.getStock());
    }
}
