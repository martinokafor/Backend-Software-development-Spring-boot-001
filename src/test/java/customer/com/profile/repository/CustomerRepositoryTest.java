package customer.com.profile.repository;

import customer.com.profile.model.Customer;
import customer.com.profile.model.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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




    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByCity() {
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

        Customer persist = testEntityManager.persist(customer_one);
        List<Customer> customers = new ArrayList<>(Arrays.asList(customer_one));
        List <Customer> customer = customerRepository.findByCity(customer_one.getCity());
        assertEquals(customer, customers);
    }

    @Test
    void findByCustomerName() {
    }
}