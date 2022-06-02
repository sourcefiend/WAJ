package hr.tvz.smolcic.hardwareapp.repository;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import hr.tvz.smolcic.hardwareapp.interfaces.repositories.IHardwareRepository;
import hr.tvz.smolcic.hardwareapp.model.Hardware;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HardwareRepository implements IHardwareRepository {

    private List<Hardware> MOCKED_HARDWARE = new ArrayList(Arrays.asList(
            new Hardware("AMD RYZEN 5 2600X", "001", 1975.45, HardwareType.CPU, 100),
            new Hardware("3VGA RTX 3060", "002", 5678.45, HardwareType.GPU, 50),
            new Hardware("ASUS B400 PRO", "003", 785.68, HardwareType.MBO, 75),
            new Hardware("PATRIOT SSD 512 GB", "004", 500, HardwareType.STORAGE, 150),
            new Hardware("HYPERX VENGEANCE 32GB RAM", "005", 725.64, HardwareType.RAM, 300)
    ));

    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        return MOCKED_HARDWARE.stream().filter(hw -> Objects.equals(hw.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if (MOCKED_HARDWARE.stream().filter(hw -> hw.getCode().equals(hardware.getCode())).findFirst().isPresent()) {
            return Optional.empty();
        }
        MOCKED_HARDWARE.add(hardware);
        return Optional.of(hardware);
    }

    @Override
    public void delete(String code) {
        if (MOCKED_HARDWARE.stream().filter(hw -> hw.getCode().equals(code)).findFirst().isPresent()) {
            Optional<Hardware> matchedHardware = MOCKED_HARDWARE.stream().filter(hw -> hw.getCode().equals(code)).findFirst();
            MOCKED_HARDWARE.remove(matchedHardware.get());
        }
    }
}
