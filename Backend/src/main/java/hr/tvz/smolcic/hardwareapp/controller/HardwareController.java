package hr.tvz.smolcic.hardwareapp.controller;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import hr.tvz.smolcic.hardwareapp.service.HardwareService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable String code) {
        return hardwareService.findByCode(code)
                .map(
                    hardwareDTO -> ResponseEntity
                            .status(HttpStatus.OK)
                            .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NO_CONTENT)
                                .build()
                );
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @DeleteMapping("/{code}")
    @Secured({"ROLE_ADMIN"})
    public void delete(@PathVariable String code) {
        hardwareService.delete(code);
    }
}
