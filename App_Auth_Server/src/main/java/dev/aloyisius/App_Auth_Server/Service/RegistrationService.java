package dev.aloyisius.App_Auth_Server.Service;

import dev.aloyisius.App_Auth_Server.Models.ApplicationUsers;
import dev.aloyisius.App_Auth_Server.Repository.AppUserRepository;
import dev.aloyisius.App_Auth_Server.Service.Exceptions.UserAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegistrationService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(ApplicationUsers users){
        appUserRepository.findByEmail(users.getEmail()).ifPresent(x -> {
            try {
                throw new UserAlreadyExistException("");
            } catch (UserAlreadyExistException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        });
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        appUserRepository.save(users);
    }
}
