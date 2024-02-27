package customer.com.profile.repository;
import customer.com.profile.model.Customer;
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
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private final String FIRST_CUSTOMER_NAME = "Martin E";
    private final String SECOND_CUSTOMER_NAME = "Martin Eme";
    private final String FIRST_CUSTOMER_CITY = "dresden";
    private final String SECOND_CUSTOMER_CITY = "berlin";

    Customer firstCustomer = Customer.builder().
            customerName(FIRST_CUSTOMER_NAME).
            city(FIRST_CUSTOMER_CITY).
            noOfVehicle(1).build();
    Customer secondCustomer = Customer.builder().
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
    void findByCity() {
        Customer persistedCustomer = testEntityManager.persist(firstCustomer);
        List<Customer> persistedCustomerList = new ArrayList<>(Arrays.asList(persistedCustomer));
        List<Customer> customer = customerRepository.findByCity(firstCustomer.getCity());
        assertEquals(customer, persistedCustomerList);
        assertEquals(customer.size(), 1);
        assertEquals(customer.get(0).getCity(), FIRST_CUSTOMER_CITY);
    }

    @Test
    void findByCustomerName() {
        Customer persistedCustomer = testEntityManager.persist(secondCustomer);
        List<Customer> persistedCustomerList = new ArrayList<>(Arrays.asList(persistedCustomer));
        List <Customer> customer = customerRepository.findByCustomerName(secondCustomer.getCustomerName());
        assertEquals(customer, persistedCustomerList);
        assertEquals(customer.size(), 1);
        assertEquals(customer.get(0).getCustomerName(), SECOND_CUSTOMER_NAME);
    }
}