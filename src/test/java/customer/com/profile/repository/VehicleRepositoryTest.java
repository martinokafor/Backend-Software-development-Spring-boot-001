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

    private final String CUSTOMER_NAME = "Martin E";
    private final String CUSTOMER_CITY = "dresden";
    private final String FIRST_VEHICLE_NAME = "Honda";
    private final String FIRST_VEHICLE_MODEL = "accord";
    private final String SECOND_VEHICLE_NAME = "Honda";
    private final String SECOND_VEHICLE_MODEL = "accord";

    Customer customer = Customer.builder().
            customerName(CUSTOMER_NAME).
            city(CUSTOMER_CITY).
            noOfVehicle(1).
            build();
    Vehicle firstVehicle = Vehicle.builder().
            vehicleName(FIRST_VEHICLE_NAME).
            model(FIRST_VEHICLE_MODEL).
            customer(customer).
            build();

    Vehicle secondVehicle = Vehicle.builder().
            vehicleName(SECOND_VEHICLE_NAME).
            customer(customer).
            model(SECOND_VEHICLE_MODEL).
            build();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByVehicleName() {
        testEntityManager.persist(customer);
        Vehicle firstPersistedVehicle = testEntityManager.persist(firstVehicle);
        Vehicle secondPersistedVehicle = testEntityManager.persist(secondVehicle);
        List<Vehicle> persistedVehicleList = new ArrayList<>(Arrays.asList(
                firstPersistedVehicle,
                secondPersistedVehicle
        ));
        List <Vehicle> vehicle = vehicleRepository.findAllByVehicleName(firstVehicle.getVehicleName());
        assertEquals(vehicle.size(), 2);
        assertEquals(persistedVehicleList, vehicle);
    }

    @Test
    void findByModel() {
        testEntityManager.persist(customer);
        Vehicle persistedVehicle = testEntityManager.persist(secondVehicle);
        List<Vehicle> persistedVehicleList = new ArrayList<>(Arrays.asList(persistedVehicle));
        List <Vehicle> vehicleList = vehicleRepository.findByModel(secondVehicle.getModel());
        assertEquals(persistedVehicleList, vehicleList);
        assertEquals(vehicleList.size(), 1);
        assertEquals(vehicleList.get(0).getModel(), SECOND_VEHICLE_MODEL);
    }

    @Test
    void findByCustomerId() {
        Customer persistedCustomer = testEntityManager.persist(customer);
        Vehicle persistedVehicle = testEntityManager.persist(secondVehicle);
        List <Vehicle> persistedVehicleList = vehicleRepository.findByCustomerId(persistedCustomer.getCustomerId());
        assertNotNull(persistedVehicleList);
        assertEquals(persistedVehicleList.size(), 1);
        assertEquals(persistedVehicleList.get(0).getCustomer(), persistedCustomer);
    }
}