package com.ecobank;

import com.ecobank.backend.persistence.domain.backend.Role;
import com.ecobank.backend.persistence.domain.backend.User;
import com.ecobank.backend.persistence.domain.backend.UserRole;
import com.ecobank.backend.service.CSVFileProcessor;
import com.ecobank.backend.service.UserService;
import com.ecobank.enums.RolesEnum;
import com.ecobank.utils.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


import java.util.HashSet;
import java.util.Set;

@SpringBootApplication


public class CallLogApp extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CallLogApp.class);

	@Autowired
	private UserService userService;

	
	@Autowired
	private CSVFileProcessor fileProcessor;
	private String username = "dpo";

	private String password = "password";

	private String email = "dpo@gmail.com";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return configureApplication(builder);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {

		return builder.sources(CallLogApp.class).bannerMode(Banner.Mode.LOG);
	}

	public static void main(String[] args) {
		SpringApplication.run(CallLogApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	

		User user = UserUtils.createBasicUser(username, email);
		user.setPassword(password);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.DPO)));
		
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, userRoles);
		LOG.info("User {} created", user.getUsername());
	}
}
