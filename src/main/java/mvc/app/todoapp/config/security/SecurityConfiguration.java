package mvc.app.todoapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager configureUserDetailsManager() {
        //hardcoded users for testing
        UserDetails userDetails1 = getUserDetails("Georgi", "123");
        UserDetails userDetails2 = getUserDetails("Georgi2", "12345");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails getUserDetails(String username, String password) {
        //withDefaultPasswordEncoder is not safe for use in production enviorment. I`m using it only for EXAMPLE.
        //Best use -> BCryptPasswordEncoder !!!
        //UserDetails userDetails = User.withDefaultPasswordEncoder()

        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*1.Two things are autoconfigured if we don`t do it manually
     * -Spring security protects all URLs
     * -and it has a default login form for unauthorized users
     *2. To access the H2 URL we need to disable CSRF also H2 website use Frames and Spring Security by default don't allow the using of Frames*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
          auth-> auth.anyRequest().authenticated()); // every request should be authenticated
        http.formLogin(Customizer.withDefaults()); // we are using the default loginForm, but we should add this line because we customize the SecurityFilterChain

        //for H2 URL to work:
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
