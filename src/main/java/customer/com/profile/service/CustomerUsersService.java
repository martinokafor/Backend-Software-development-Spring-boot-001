package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.model.CustomerUsers;
import customer.com.profile.model.Vehicle;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.CustomerUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerUsersService {
    @Autowired
    CustomerUsersRepository customerUsersRepository;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerUsers createCustomerUsers(CustomerUsers customerUsers, Integer customerId){
        Customer customer = customerRepository.findById(customerId).get();
        customerUsers.setCustomer(customer);
        return customerUsersRepository.save(customerUsers);
    }

    public CustomerUsers updateCustomerUsers(Integer id, List<Vehicle> vin){
        CustomerUsers customerUsers = customerUsersRepository.findById(id).get();
        List<Vehicle> vehicle = customerUsers.getVehicle();
        vehicle.addAll(vin);
        customerUsers.setVehicle(vehicle);
        return customerUsersRepository.save(customerUsers);
    }

    public List<CustomerUsers> getCustomerUsers(Integer customerId){
        return customerUsersRepository.findCustomerUsersByCustomerId(customerId);
    }
}
