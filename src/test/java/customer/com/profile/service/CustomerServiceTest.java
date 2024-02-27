package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.repository.CustomerRepository;
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
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    private final String FIRST_CUSTOMER_NAME = "Martin E";
    private final String SECOND_CUSTOMER_NAME = "Martin Eme";
    private final String FIRST_CUSTOMER_CITY = "dresden";
    private final String SECOND_CUSTOMER_CITY = "dresden";
    Customer firstCustomer = Customer.builder().
            customerId(1).
            customerName(FIRST_CUSTOMER_NAME).
            city(FIRST_CUSTOMER_CITY).
            noOfVehicle(1).build();
    Customer secondCustomer = Customer.builder().
            customerId(20).
            customerName(SECOND_CUSTOMER_NAME).
            city(SECOND_CUSTOMER_CITY).
            noOfVehicle(1).build();


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllCustomers() {
        List<Customer> customers = new ArrayList<>(Arrays.asList(firstCustomer, secondCustomer));
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(customerService.fetchAllCustomers(), customers);
        assertEquals(customerService.fetchAllCustomers().size(), 2);
    }

    @Test
    void createCustomer() {
        Mockito.when(customerRepository.save(firstCustomer)).thenReturn(firstCustomer);
        Customer customer = customerService.CreateCustomer(firstCustomer);
        assertEquals(customer, firstCustomer);
        assertEquals(customer.getCustomerName(), firstCustomer.getCustomerName());
    }

    @Test
    void getCustomer() {
        Mockito.when(
                customerRepository.findById(
                        firstCustomer.getCustomerId())).thenReturn(Optional.ofNullable(firstCustomer));
        Customer customer = customerService.getCustomer(firstCustomer.getCustomerId());
        assertEquals(customer, firstCustomer);
    }

    @Test
    void deleteCustomer() {
        assertEquals(customerService.deleteCustomer(firstCustomer.getCustomerId()), 0);
    }

    @Test
    void findByCity() {
        List<Customer> customer = new ArrayList<>(Arrays.asList(firstCustomer, secondCustomer));
        Mockito.when(customerRepository.findByCity(firstCustomer.getCity())).thenReturn(customer);
        assertEquals(customerService.findByCity(firstCustomer.getCity()), customer);
        assertEquals(customerService.findByCity(firstCustomer.getCity()).size(), 2);
    }

    @Test
    void findByCustomerName() {
        List<Customer> customer = new ArrayList<>(Arrays.asList(firstCustomer));
        Mockito.when(customerRepository.findByCustomerName(firstCustomer.getCustomerName())).thenReturn(customer);
        assertEquals(customerService.findByCustomerName(firstCustomer.getCustomerName()), customer);
    }
}