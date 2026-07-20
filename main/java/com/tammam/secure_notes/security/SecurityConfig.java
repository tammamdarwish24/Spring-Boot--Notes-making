package com.tammam.secure_notes.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.tammam.secure_notes.models.AppRole;
import com.tammam.secure_notes.models.Role;
import com.tammam.secure_notes.models.User;
import com.tammam.secure_notes.repositories.RoleRepository;
import com.tammam.secure_notes.repositories.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

import java.time.LocalDate;
import java.util.function.Supplier;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
				                                         
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
	@Bean
	public CommandLineRunner initData(RoleRepository roleRepo,UserRepository userRepo) {
		return args->{ Role user_role=roleRepo.findByRoleName(AppRole.ROLE_USER).orElseGet(()-> roleRepo.save(new Role(AppRole.ROLE_USER)));
	                   Role admin_role=roleRepo.findByRoleName(AppRole.ROLE_ADMIN).orElseGet(()-> roleRepo.save(new Role(AppRole.ROLE_ADMIN)));
	           
	                   if (!userRepo.existsByUserName("user1"))
	                   {
	                	   User user1= new User("user1","user1@gmail.com","{noop}user123");
	                	   user1.setAccountNonLocked(true);
	                	   user1.setAccountNonExpired(true);
	                	   user1.setCredentilasNonExpired(true);
	                	   user1.setEnabled(true);
	                	   user1.setCrdentilasExpiryDate(LocalDate.now().plusYears(1));
	                	   user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
	                	   user1.setIstwoFactorEnabled(false);
	                	   user1.setSignUpMethod("email");
	                	   user1.setRole(user_role);
	                	   userRepo.save(user1);
	                   }
	          
		
	                   if (!userRepo.existsByUserName("admin")) {
	                       User admin = new User("admin", "admin@example.com", "{noop}adminPass");
	                       admin.setAccountNonLocked(true);
	                       admin.setAccountNonExpired(true);
	                       admin.setCredentilasNonExpired(true);
	                       admin.setEnabled(true);
	                       admin.setCrdentilasExpiryDate(LocalDate.now().plusYears(1));
	                       admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
	                       admin.setIstwoFactorEnabled(false);
	                       admin.setSignUpMethod("email");
	                       admin.setRole(admin_role);
	                       userRepo.save(admin);
	                   }
		
		
	
		
	
};
}
}

/*   @Bean
    public CommandLineRunner initDatabase() {
        // Here we are creating an anonymous class implementing CommandLineRunner
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Initializing data using an anonymous class.");
                
                // Example of using the args parameter
                if (args.length > 0) {
                    System.out.println("First argument: " + args[0]);
                }
            }*/
