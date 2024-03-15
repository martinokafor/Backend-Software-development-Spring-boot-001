package customer.com.profile.config;

import customer.com.profile.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConfigTest {
    @Autowired
    CustomerConfig customerConfig;
    @Autowired
    VehicleConfig vehicleConfig;

    @Test
    void checkCustomerConfig() {
        assertEquals(customerConfig.getMaxNoOfCustomers(), "20");
        assertEquals(customerConfig.isEnabled(), true);
    }
    @Test
    void checkVehicleConfig() {
        assertEquals(vehicleConfig.getMaxNoOfVehicles(), "20");
        assertEquals(vehicleConfig.isEnabled(), true);
    }
}
