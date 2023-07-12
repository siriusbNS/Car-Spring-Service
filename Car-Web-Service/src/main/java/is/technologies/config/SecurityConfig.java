package is.technologies.config;

import is.technologies.entities.Person;
import is.technologies.repositories.PeopleRepository;
import is.technologies.security.AuthProviderImpl;
import is.technologies.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin","/api/BrandOfCars/**").hasRole("ADMIN")
                .antMatchers("/api/ModeOfCars/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/api/EnginesOfCars/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/here/registration","/error","/here/login")
                .permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().loginPage("/here/login")
                .loginProcessingUrl("/here/login/process_login")
                .defaultSuccessUrl("/swagger-ui/")
                .failureUrl("/here/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/here/login");

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
