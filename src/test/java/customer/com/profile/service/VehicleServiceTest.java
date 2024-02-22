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

    Vehicle vehicle_one = Vehicle.builder().
            vehicleName("honda").
            vin("6c628184-bb2a-4114-bc71-a3dc328d7136").
            model("accord").build();
    Vehicle vehicle_two = Vehicle.builder().
            vehicleName("volkswagen").
            vin("3dfb857f-9dff-46c6-a024-6d8c86f323a5").
            model("touareg").build();
    Customer customer = Customer.builder().
            customerId(1).
            customerName("Martin E").
            city("dresden").
            noOfVehicle(1).build();
    List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(vehicle_one, vehicle_two));

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
    }

    @Test
    void createVehicle() {
        Mockito.when(vehicleRepository.save(vehicle_one)).thenReturn(vehicle_one);
        assertEquals(vehicleRepository.save(vehicle_one), vehicle_one);
    }

    @Test
    void getVehicle() {
        Mockito.when(vehicleRepository.findById(vehicle_one.getVin())).thenReturn(Optional.ofNullable(vehicle_one));
        assertEquals(vehicleService.getVehicle(vehicle_one.getVin()), vehicle_one);
    }

    @Test
    void findAllByVehicleName() {
        Mockito.when(vehicleRepository.findAllByVehicleName(vehicle_one.getVehicleName())).thenReturn(vehicles);
        assertEquals(vehicleService.findAllByVehicleName(vehicle_one.getVehicleName()), vehicles);
    }

    @Test
    void findByModel() {
        Mockito.when(vehicleRepository.findByModel(vehicle_one.getModel())).thenReturn(vehicles);
        assert vehicleService.findByModel(vehicle_one.getModel()) == vehicles;
    }

    @Test
    void findVehiclesByCustomerId() {
        Mockito.when(vehicleRepository.findByCustomerId(customer.getCustomerId())).thenReturn(vehicles);
        assert vehicleService.findVehiclesByCustomerId(customer.getCustomerId()) == vehicles;
    }

    @Test
    void countVehiclesByCustomerId() {
        Mockito.when(vehicleRepository.findByCustomerId(customer.getCustomerId())).thenReturn(vehicles);
        assert vehicleService.countVehiclesByCustomerId(customer.getCustomerId()) == 2;
    }

    @Test
    void deleteVehicleByVin() {
        assert vehicleService.deleteVehicleByVin(vehicle_one.getVin()) == 0;
    }
}