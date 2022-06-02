package hr.tvz.smolcic.hardwareapp.interfaces.services;

import hr.tvz.smolcic.hardwareapp.DTOs.LoginDTO;
import hr.tvz.smolcic.hardwareapp.command.LoginCommand;

import java.util.Optional;

public interface IAuthenticationService {
    Optional<LoginDTO> login(LoginCommand command);
}
