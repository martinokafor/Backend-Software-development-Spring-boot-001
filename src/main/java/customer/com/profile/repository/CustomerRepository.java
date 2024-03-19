package customer.com.profile.repository;

import customer.com.profile.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCity(String city);

    List<Customer> findByCustomerName(String customerName);

}
