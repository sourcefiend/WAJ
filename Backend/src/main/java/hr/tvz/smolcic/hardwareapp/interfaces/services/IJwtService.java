package hr.tvz.smolcic.hardwareapp.interfaces.services;

import hr.tvz.smolcic.hardwareapp.model.User;

public interface IJwtService {

    boolean authenticate(String token);

    String createJwt(User user);
}
