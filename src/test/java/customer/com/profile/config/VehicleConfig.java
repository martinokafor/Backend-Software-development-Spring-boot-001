package customer.com.profile.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.spring-test.vehicles")
public class VehicleConfig {
    public String maxNoOfVehicles;
    public boolean enabled;

    public void setMaxNoOfVehicles(String maxNoOfVehicles) {
        this.maxNoOfVehicles = maxNoOfVehicles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
