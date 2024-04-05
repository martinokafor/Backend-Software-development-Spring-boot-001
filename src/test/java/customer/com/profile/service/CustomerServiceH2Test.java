package customer.com.profile.service;

import customer.com.profile.config.CustomerConfig;
import customer.com.profile.config.VehicleConfig;
import customer.com.profile.model.Customer;
import customer.com.profile.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerServiceH2Test {
    @Autowired
    private CustomerService customerService;
    @Autowired
    CustomerConfig customerConfig;
    private final String FIRST_CUSTOMER_NAME = "Martin E";
    private final String SECOND_CUSTOMER_NAME = "Martin Eme";
    private final String THIRD_CUSTOMER_NAME = "Mr Martin";
    private final String FIRST_CUSTOMER_CITY = "dresden";
    private final String SECOND_CUSTOMER_CITY = "dresden";
    private final String THIRD_CUSTOMER_CITY = "berlin";

    Customer firstCustomer = Customer.builder().
            customerName(FIRST_CUSTOMER_NAME).
            city(FIRST_CUSTOMER_CITY).
            noOfVehicle(1).
            build();
    Customer secondCustomer = Customer.builder().
            customerName(SECOND_CUSTOMER_NAME).
            city(SECOND_CUSTOMER_CITY).
            noOfVehicle(1).
            build();
    Customer thirdCustomer = Customer.builder().
            customerName(THIRD_CUSTOMER_NAME).
            city(THIRD_CUSTOMER_CITY).
            noOfVehicle(2).
            build();

    @BeforeEach
    void setUp() {
        customerService.CreateCustomer(firstCustomer);
        customerService.CreateCustomer(secondCustomer);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchAllCustomer() {
        List<Customer> customers = customerService.fetchAllCustomers();
        assertEquals(customers.size(), 2);
        assertTrue(Integer.parseInt(customerConfig.getMaxNoOfCustomers()) > customers.size());
    }

    @Test
    void getCustomer(){
        Customer customer = customerService.getCustomer(1);
        assertNotNull(customer);
        assertEquals(customer.getCustomerName(), firstCustomer.getCustomerName());
    }

    @Test
    void createCustomer(){
        Customer customer = customerService.CreateCustomer(secondCustomer);
        assertNotNull(customer);
        assertEquals(customer.getCity(), secondCustomer.getCity());
    }

    @Test
    void updateCustomer() {
        Customer customer = customerService.updateCustomer(thirdCustomer, firstCustomer.getCustomerId());
        assertNotNull(customer);
        assertEquals(customer.getCustomerName(), thirdCustomer.getCustomerName());
        assertNotEquals(customer.getCustomerName(), firstCustomer.getCustomerName());
    }

    @Test
    void deleteCustomer(){
        double deletedCustomer = customerService.deleteCustomer(firstCustomer.getCustomerId());
        assertEquals(deletedCustomer, 0);
    }

    @Test
    void findByCity(){
        List <Customer> customers = customerService.findByCity(firstCustomer.getCity());
        assertNotNull(customers);
        assertEquals(customers.get(0).getCity(), firstCustomer.getCity());
        assertEquals(customers.get(1).getCity(), secondCustomer.getCity());
    }

    @Test
    void findByCustomerName(){
        List <Customer> customers = customerService.findByCustomerName(firstCustomer.getCustomerName());
        assertNotNull(customers);
        assertEquals(customers.get(0).getCustomerName(), firstCustomer.getCustomerName());
    }
}