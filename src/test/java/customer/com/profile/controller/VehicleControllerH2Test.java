package customer.com.profile.controller;

import customer.com.profile.model.Customer;
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

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class VehicleControllerH2Test {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CustomerService customerService;

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
            build();

    Vehicle secondVehicle = Vehicle.builder().
            vehicleName(SECOND_VEHICLE_NAME).
            model(SECOND_VEHICLE_MODEL).
            customer(customer).
            build();

    @BeforeEach
    void setUp() {
        Customer createCustomer = customerService.CreateCustomer(customer);
        vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId());
        vehicleService.CreateVehicle(secondVehicle, createCustomer.getCustomerId());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllVehicle() {
        List <Vehicle> vehicles = vehicleService.fetchAllVehicles();
        assertNotNull(vehicles);
    }

    @Test
    void getVehicle(){
        Vehicle vehicle = vehicleService.getVehicle(firstVehicle.getVin());
        assertEquals(vehicle.getVin(), firstVehicle.getVin());
    }

    @Test
    void createVehicle(){
        Customer createCustomer = customerService.CreateCustomer(customer);
        vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId());
        vehicleService.CreateVehicle(secondVehicle, createCustomer.getCustomerId());
    }

    @Test
    void findVehicleByName(){
        List <Vehicle> vehicle = vehicleService.findAllByVehicleName(SECOND_VEHICLE_NAME );
        assertEquals(vehicle.get(1).getVehicleName(), firstVehicle.getVehicleName());
    }

    @Test
    void findVehiclesByCustomerId() {
        List<Vehicle> vehicle = vehicleService.findVehiclesByCustomerId(1);
        assertEquals(vehicle.size(), 2);
    }

    @Test
    void countVehiclesByCustomerId(){
        double vehicleCount = vehicleService.countVehiclesByCustomerId(1);
        assertEquals(vehicleCount, 2);
    }

    @Test
    void deleteVehicleByVin(){
        Customer createCustomer = customerService.CreateCustomer(customer);
        Vehicle createdVehicle = vehicleService.CreateVehicle(firstVehicle, createCustomer.getCustomerId());
        double deletedVehicle = vehicleService.deleteVehicleByVin(createdVehicle.getVin());
        assertEquals(deletedVehicle, 0);
    }
}