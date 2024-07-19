package customer.com.profile.service;

import customer.com.profile.config.VehicleConfig;
import customer.com.profile.model.Customer;
import customer.com.profile.model.Order;
import customer.com.profile.model.Vehicle;
import customer.com.profile.service.CustomerService;
import customer.com.profile.service.VehicleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class VehicleServiceH2Test {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    VehicleConfig vehicleConfig;

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

    Order order = Order.builder().
            brand(CUSTOMER_NAME).
            price(CUSTOMER_CITY).
            currency("Euro").
            consent(true).
            build();

    Vehicle firstVehicle = Vehicle.builder().
            vehicleName(FIRST_VEHICLE_NAME).
            model(FIRST_VEHICLE_MODEL).
            build();

    Vehicle secondVehicle = Vehicle.builder().
            vehicleName(SECOND_VEHICLE_NAME).
            model(SECOND_VEHICLE_MODEL).
            customer(customer).
            build();

    @BeforeEach
    void setUp() {
        Customer createCustomer = customerService.CreateCustomer(customer);
        vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId(), order.getOrderId());
        vehicleService.CreateVehicle(secondVehicle, createCustomer.getCustomerId(), order.getOrderId());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllVehicle() {
        List <Vehicle> vehicles = vehicleService.fetchAllVehicles();
        assertNotNull(vehicles);
        assertTrue(Integer.parseInt(vehicleConfig.getMaxNoOfVehicles()) > vehicles.size());
    }

    @Test
    void getVehicle(){
        Vehicle vehicle = vehicleService.getVehicle(firstVehicle.getVin());
        assertEquals(vehicle.getVin(), firstVehicle.getVin());
    }

    @Test
    void createVehicle(){
        Customer createCustomer = customerService.CreateCustomer(customer);
        vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId(), order.getOrderId());
        vehicleService.CreateVehicle(secondVehicle, createCustomer.getCustomerId(), order.getOrderId());
    }

    @Test
    void findVehicleByName(){
        List <Vehicle> vehicle = vehicleService.findAllByVehicleName(SECOND_VEHICLE_NAME );
        assertEquals(vehicle.get(1).getVehicleName(), firstVehicle.getVehicleName());
    }

    @Test
    void findVehiclesByCustomerId() {
        List<Vehicle> vehicle = vehicleService.findVehiclesByCustomerId(1);
        assertNotNull(vehicle.size());
    }

    @Test
    void countVehiclesByCustomerId(){
        double vehicleCount = vehicleService.countVehiclesByCustomerId(1);
        assertNotNull(vehicleCount);
    }

    @Test
    void deleteVehicleByVin(){
        Customer createCustomer = customerService.CreateCustomer(customer);
        Vehicle createdVehicle = vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId(), order.getOrderId());
        double deletedVehicle = vehicleService.deleteVehicleByVin(createdVehicle.getVin());
        assertEquals(deletedVehicle, 0);
    }
}