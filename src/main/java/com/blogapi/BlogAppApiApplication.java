package com.blogapi;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapi.entity.Role;
import com.blogapi.repository.RoleRepository;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
	
		try {
			Role role = new Role();
			role.setRole_id(1);
			role.setRoleName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role2.setRole_id(2);
			role2.setRoleName("ROLE_USER");
			
			List<Role> result = new ArrayList<>();
			result.add(role2);
			result.add(role);
			this.roleRepository.saveAll(result);
		}
		catch(Exception e)
		{
			throw e ;
		}
		
	}
    
    
}
