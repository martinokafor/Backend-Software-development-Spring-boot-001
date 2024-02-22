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

    Customer customer_one = Customer.builder().
            customerId(1).
            customerName("Martin E").
            city("dresden").
            noOfVehicle(1).build();
    Customer customer_two = Customer.builder().
            customerId(20).
            customerName("Mart E").
            city("berlin").
            noOfVehicle(1).build();


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllCustomers() {

        List<Customer> customers = new ArrayList<>(Arrays.asList(customer_one, customer_two));
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(customerService.fetchAllCustomers(), customers);
    }

    @Test
    void createCustomer() {
        Mockito.when(customerRepository.save(customer_one)).thenReturn(customer_one);
        assertEquals(customerService.CreateCustomer(customer_one), customer_one);
    }

    @Test
    void getCustomer() {
        Mockito.when(
                customerRepository.findById(
                        customer_one.getCustomerId())).thenReturn(Optional.ofNullable(customer_one));
        assertEquals(customerService.getCustomer(customer_one.getCustomerId()), customer_one);
    }

    @Test
    void deleteCustomer() {
        assertEquals(customerService.deleteCustomer(customer_two.getCustomerId()), 0);
        System.out.println(customer_two.getCustomerId());
    }

    @Test
    void findByCity() {
        List<Customer> customer = new ArrayList<>(Arrays.asList(customer_one));
        Mockito.when(customerRepository.findByCity("dresden")).thenReturn(customer);
        assertEquals(customerService.findByCity("dresden"), customer);
    }

    @Test
    void findByCustomerName() {
        List<Customer> customer = new ArrayList<>(Arrays.asList(customer_one));
        Mockito.when(customerRepository.findByCustomerName("Martin E")).thenReturn(customer);
        assertEquals(customerService.findByCustomerName("Martin E"), customer);
    }
}