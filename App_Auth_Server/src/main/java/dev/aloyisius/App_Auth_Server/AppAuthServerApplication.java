package dev.aloyisius.App_Auth_Server;

import dev.aloyisius.App_Auth_Server.Models.ApplicationUsers;
import dev.aloyisius.App_Auth_Server.Service.RegistrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAuthServerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RegistrationService registrationService){
		return args -> {
			ApplicationUsers user1 = new ApplicationUsers();
			user1.setEmail("bill");
			user1.setPassword("12345");
			user1.setAuthority("user");

			registrationService.registerUser(user1);

		};
	}

}
