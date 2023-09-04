package com.akshat.bog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.akshat.bog.config.AppConstants;
import com.akshat.bog.entities.Role;
import com.akshat.bog.repository.RoleRepo;

@SpringBootApplication

public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("akshat@123"));

		try {
			Role roleadmin = new Role();
			roleadmin.setId(AppConstants.ADMIN_USER);
			roleadmin.setName("ROLE_ADMIN");

			Role roleuser = new Role();
			roleuser.setId(AppConstants.NORMAL_USER);
			roleuser.setName("ROLE_USER");
			List<Role> roles = List.of(roleadmin, roleuser);
			this.roleRepo.saveAll(roles);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
