package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.model.Vehicle;
import customer.com.profile.repository.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleServiceTest {
    @MockBean
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    private final String CUSTOMER_NAME = "Martin E";
    private final String CUSTOMER_CITY = "dresden";
    private final String FIRST_VEHICLE_NAME = "Honda";
    private final String FIRST_VEHICLE_MODEL = "accord";
    private final String FIRST_VEHICLE_VIN = "6c628184-bb2a-4114-bc71-a3dc328d7136";
    private final String SECOND_VEHICLE_NAME = "Honda";
    private final String SECOND_VEHICLE_MODEL = "accord";
    private final String SECOND_VEHICLE_VIN = "3dfb857f-9dff-46c6-a024-6d8c86f323a5";

    Customer customer = Customer.builder().
            customerId(1).
            customerName(CUSTOMER_NAME).
            city(CUSTOMER_CITY).
            noOfVehicle(1).build();
    Vehicle firstVehicle = Vehicle.builder().
            vehicleName(FIRST_VEHICLE_NAME).
            vin(FIRST_VEHICLE_VIN).
            customer(customer).
            model(FIRST_VEHICLE_MODEL).build();
    Vehicle secondVehicle = Vehicle.builder().
            vehicleName(SECOND_VEHICLE_NAME ).
            vin(SECOND_VEHICLE_VIN).
            customer(customer).
            model(SECOND_VEHICLE_MODEL).build();

    List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(firstVehicle, secondVehicle));

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllVehicles() {
        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
        assertEquals(vehicleService.fetchAllVehicles(), vehicles);
        assertEquals(vehicles.size(),2);
    }

    @Test
    void createVehicle() {
        Mockito.when(vehicleRepository.save(firstVehicle)).thenReturn(firstVehicle);
        assertEquals(vehicleRepository.save(firstVehicle), firstVehicle);
    }

    @Test
    void getVehicle() {
        Mockito.when(vehicleRepository.findById(firstVehicle.getVin())).thenReturn(Optional.ofNullable(firstVehicle));
        assertEquals(vehicleService.getVehicle(firstVehicle.getVin()), firstVehicle);
        assertNotEquals(vehicleService.getVehicle(firstVehicle.getVin()), secondVehicle);
    }

    @Test
    void findAllByVehicleName() {
        Mockito.when(vehicleRepository.findAllByVehicleName(firstVehicle.getVehicleName())).thenReturn(vehicles);
        assertEquals(vehicleService.findAllByVehicleName(firstVehicle.getVehicleName()), vehicles);
        assertEquals(vehicles.size(),2);
    }

    @Test
    void findByModel() {
        Mockito.when(vehicleRepository.findByModel(firstVehicle.getModel())).thenReturn(vehicles);
        assert vehicleService.findByModel(firstVehicle.getModel()) == vehicles;
        assertEquals(vehicles.size(),2);
    }

    @Test
    void findVehiclesByCustomerId() {
        Mockito.when(vehicleRepository.findByCustomerId(customer.getCustomerId())).thenReturn(vehicles);
        assert vehicleService.findVehiclesByCustomerId(customer.getCustomerId()) == vehicles;
        assertEquals(vehicles.size(),2);
    }

    @Test
    void countVehiclesByCustomerId() {
        Mockito.when(vehicleRepository.findByCustomerId(customer.getCustomerId())).thenReturn(vehicles);
        assert vehicleService.countVehiclesByCustomerId(customer.getCustomerId()) == 2;
    }

    @Test
    void deleteVehicleByVin() {
        assert vehicleService.deleteVehicleByVin(firstVehicle.getVin()) == 0;
    }
}