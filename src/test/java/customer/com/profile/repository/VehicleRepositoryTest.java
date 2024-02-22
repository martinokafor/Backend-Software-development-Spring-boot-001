package customer.com.profile.repository;

import customer.com.profile.model.Customer;
import customer.com.profile.model.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VehicleRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private VehicleRepository vehicleRepository;


    Customer customer = Customer.builder().
            customerName("Martin E").
            city("dresden").
            noOfVehicle(1).
            build();
    Vehicle vehicle_one = Vehicle.builder().
            vehicleName("honda").
            model("accord").
            customer(customer).
            build();

    Vehicle vehicle_two = Vehicle.builder().
            vehicleName("volkswagen").
            customer(customer).
            model("touareg").
            build();


    List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle_one, vehicle_two));

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByVehicleName() {
        Vehicle persistVehicle = testEntityManager.persist(vehicle_one);
        List<Vehicle> persistedVehicle = new ArrayList<>(Arrays.asList(persistVehicle));
        List <Vehicle> vehicle = vehicleRepository.findAllByVehicleName(vehicle_one.getVehicleName());
        assertEquals(persistedVehicle, vehicle);
    }

    @Test
    void findByModel() {
        Vehicle persistVehicle = testEntityManager.persist(vehicle_two);
        List<Vehicle> persistedVehicle = new ArrayList<>(Arrays.asList(persistVehicle));
        List <Vehicle> vehicle = vehicleRepository.findByModel(vehicle_two.getModel());
        assertEquals(persistedVehicle, vehicle);
    }

    @Test
    void findByCustomerId() {
        Customer persistCustomer = testEntityManager.persist(customer);
        //List<Vehicle> persistedVehicle = new ArrayList<>(Arrays.asList(persistCustomer));
        List <Vehicle> persistedVehicle = vehicleRepository.findByCustomerId(customer.getCustomerId());
        assertNotNull(persistedVehicle);
    }
}