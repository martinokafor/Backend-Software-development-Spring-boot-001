package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Customer> fetchAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer CreateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Integer customerId){
        return customerRepository.findById(customerId).get();
    }

    public Customer updateCustomer(Customer customer, Integer customerId){
        customerRepository.findById(customerId).get();
        customer.setCustomerId(customer.getCustomerId());
        customer.setCustomerName(customer.getCustomerName());
        customer.setCity(customer.getCity());
        int noOfVehicle = vehicleRepository.findByCustomerId(customerId).size();
        customer.setNoOfVehicle(noOfVehicle);
        customerRepository.save(customer);
        return customer;
    }

    public double deleteCustomer(Integer customerId){
        customerRepository.deleteById(customerId);
        return 0;
    }

    public List<Customer> findByCity(String city){
        return customerRepository.findByCity(city);
    }

    public List<Customer> findByCustomerName(String customerName){
        return customerRepository.findByCustomerName(customerName);
    }
}
