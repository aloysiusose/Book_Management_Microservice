package dev.aloyisius.App_Auth_Server.Repository;

import dev.aloyisius.App_Auth_Server.Models.ApplicationUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<ApplicationUsers, Long> {

    Optional<ApplicationUsers> findByEmail(String email);
}
