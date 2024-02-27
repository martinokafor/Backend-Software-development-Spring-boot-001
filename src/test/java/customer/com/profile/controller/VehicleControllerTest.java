package customer.com.profile.controller;

import customer.com.profile.model.Customer;
import customer.com.profile.model.Vehicle;
import customer.com.profile.service.VehicleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {
    @MockBean
    private VehicleService vehicleService;

    @Autowired
    MockMvc mockMvc;
    private final String CUSTOMER_NAME = "Martin E";
    private final String CUSTOMER_CITY = "dresden";
    private final String FIRST_VEHICLE_NAME = "Honda";
    private final String FIRST_VEHICLE_MODEL = "accord";
    private final String SECOND_VEHICLE_NAME = "Honda";
    private final String SECOND_VEHICLE_MODEL = "accord";

    Customer customer = Customer.builder().
            customerName(CUSTOMER_NAME).
            city(CUSTOMER_CITY).
            customerId(1).
            noOfVehicle(1).
            build();

    Vehicle firstVehicle = Vehicle.builder().
            vehicleName(FIRST_VEHICLE_NAME).
            model(FIRST_VEHICLE_MODEL).
            customer(customer).
            vin("6c628184-bb2a-4114-bc71-a3dc328d7136").
            build();

    Vehicle secondVehicle = Vehicle.builder().
            vehicleName(SECOND_VEHICLE_NAME).
            customer(customer).
            model(SECOND_VEHICLE_MODEL).
            vin("3dfb857f-9dff-46c6-a024-6d8c86f323a5").
            build();

    List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(firstVehicle, secondVehicle));


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllVehicle() throws Exception {
        Mockito.when(vehicleService.fetchAllVehicles()).thenReturn(vehicles);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0]").value(firstVehicle))
                .andExpect(jsonPath("$[1]").value(secondVehicle))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getVehicle() throws Exception {
        Mockito.when(vehicleService.getVehicle(firstVehicle.getVin())).thenReturn(firstVehicle);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/vin/6c628184-bb2a-4114-bc71-a3dc328d7136")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.vin").value(firstVehicle.getVin()))
                .andExpect(jsonPath("$.*", hasSize(4)));
    }

    @Test
    void createVehicle() throws Exception {
        Mockito.when(vehicleService.CreateVehicle(secondVehicle, customer.getCustomerId())).thenReturn(secondVehicle);
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle/customer/2")
                        .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                                "    \"vehicleName\": \"Honda\",\n" +
                                "    \"model\": \"accord\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findVehicleByName() throws Exception {
        Mockito.when(vehicleService.findAllByVehicleName(secondVehicle.getVehicleName())).thenReturn(vehicles);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/vehicle_name/Honda")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].vehicleName").value(firstVehicle.getVehicleName()))
                .andExpect(jsonPath("$[1].vehicleName").value(secondVehicle.getVehicleName()))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void findVehiclesByCustomerId() throws Exception {
        Mockito.when(vehicleService.findVehiclesByCustomerId(customer.getCustomerId())).thenReturn(vehicles);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/customer_id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0]").value(firstVehicle))
                .andExpect(jsonPath("$[1]").value(secondVehicle))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void countVehiclesByCustomerId() throws Exception {
        Mockito.when(vehicleService.countVehiclesByCustomerId(customer.getCustomerId())).thenReturn(2);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").value(2));
    }

    @Test
    void deleteVehicleByVin() throws Exception {
        Mockito.when(
                vehicleService.deleteVehicleByVin(firstVehicle.getVin())).getMock();
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicle/6c628184-bb2a-4114-bc71-a3dc328d7136")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}