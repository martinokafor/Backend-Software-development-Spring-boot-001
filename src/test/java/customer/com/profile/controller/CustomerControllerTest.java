package customer.com.profile.controller;

import customer.com.profile.model.Customer;
import customer.com.profile.service.CustomerService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    private final String FIRST_CUSTOMER_NAME = "Martin E";
    private final String SECOND_CUSTOMER_NAME = "Martin Eme";
    private final String FIRST_CUSTOMER_CITY = "dresden";
    private final String SECOND_CUSTOMER_CITY = "dresden";

    Customer firstCustomer = Customer.builder().
            customerName(FIRST_CUSTOMER_NAME).
            city(FIRST_CUSTOMER_CITY).
            noOfVehicle(1).
            customerId(1).
            build();
    Customer secondCustomer = Customer.builder().
            customerName(SECOND_CUSTOMER_NAME).
            city(SECOND_CUSTOMER_CITY).
            noOfVehicle(1).
            customerId(2).
            build();
    List<Customer> customers = new ArrayList<>(Arrays.asList(firstCustomer, secondCustomer));

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllCustomer() throws Exception {

        Mockito.when(customerService.fetchAllCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].customerName").value(firstCustomer.getCustomerName()));
    }

    @Test
    void getCustomer() throws Exception {
        Mockito.when(customerService.getCustomer(firstCustomer.getCustomerId())).thenReturn(firstCustomer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.customerId").value(1));
    }

    @Test
    void createCustomer() throws Exception {
        Mockito.when(customerService.CreateCustomer(firstCustomer)).thenReturn(firstCustomer);
        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                                "    \"customerName\": \"Martin E\",\n" +
                                "    \"city\": \"dresden\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateCustomer() throws Exception {
        Mockito.when(
                customerService.updateCustomer(
                        secondCustomer, secondCustomer.getCustomerId())).thenReturn(firstCustomer);
        mockMvc.perform(MockMvcRequestBuilders.put("/customer/2")
                        .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"customerName\": \"Martin E\",\n" +
                "    \"city\": \"dresden\"\n" +
                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteCustomer() throws Exception {
        Mockito.when(
                customerService.deleteCustomer(secondCustomer.getCustomerId())).getMock();
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findByCity() throws Exception {
        Mockito.when(customerService.findByCity(secondCustomer.getCity())).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/city/dresden")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].city").value(firstCustomer.getCity()))
                .andExpect(jsonPath("$[1].city").value(secondCustomer.getCity()));
    }

    @Test
    void findByCustomerName() throws Exception {
        List<Customer> firstCustomerList = new ArrayList<>(Arrays.asList(firstCustomer));
        Mockito.when(customerService.findByCustomerName(firstCustomer.getCustomerName())).thenReturn(firstCustomerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/customer_name/Martin E")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].customerName").value(firstCustomer.getCustomerName()));
    }
}