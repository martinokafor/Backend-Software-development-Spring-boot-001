package customer.com.profile.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring-test.vehicles")
public class CustomerConfig {
    public String maxNoOfCustomers;
    public boolean enabled;

    public void setMaxNoOfVehicles(String maxNoOfVehicles) {
        this.maxNoOfCustomers = maxNoOfVehicles;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
