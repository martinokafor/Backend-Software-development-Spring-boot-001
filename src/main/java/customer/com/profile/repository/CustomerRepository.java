package customer.com.profile.repository;

import customer.com.profile.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCity(String city);

    List<Customer> findByCustomerName(String customerName);

}
