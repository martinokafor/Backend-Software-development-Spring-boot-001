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

    Customer customer_one = Customer.builder().
            customerName("Martin E").
            city("dresden").
            noOfVehicle(1).build();
    Customer customer_two = Customer.builder().
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
    void findByCity() {
        Customer persistCustomer = testEntityManager.persist(customer_one);
        List<Customer> persistedCustomer = new ArrayList<>(Arrays.asList(persistCustomer));
        List <Customer> customer = customerRepository.findByCity(customer_one.getCity());
        assertEquals(customer, persistedCustomer);
    }

    @Test
    void findByCustomerName() {
        Customer persistCustomer = testEntityManager.persist(customer_two);
        List<Customer> persistedCustomer = new ArrayList<>(Arrays.asList(persistCustomer));
        List <Customer> customer = customerRepository.findByCustomerName(customer_two.getCustomerName());
        assertEquals(customer, persistedCustomer);
    }
}