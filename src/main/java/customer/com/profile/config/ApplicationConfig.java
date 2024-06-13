package customer.com.profile.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public interface ApplicationConfig {

    void configure(HttpSecurity http) throws Exception;
}
