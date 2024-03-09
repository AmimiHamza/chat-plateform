package com.chat.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chat.demo.users.User;
import com.chat.demo.users.UserRepo;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}
	@Bean
    CommandLineRunner commandLineRunner(UserRepo userRepo){
        return args -> {
            userRepo.save(new User(null,"hamza","hamimi@insea.ac.ma","hellohamza"));
            userRepo.save(new User(null,"zakaria","zamimi@insea.ac.ma","hellozakaria"));
            userRepo.save(new User(null,"ahmed","aamimi@insea.ac.ma","helloahmed"));
            userRepo.save(new User(null,"mohammed","mamimi@insea.ac.ma","hellomohammed"));
			userRepo.findAll().forEach(u->{
				System.out.println(u.getName()+" has been added to the database");
			} );
        };
    }

}
