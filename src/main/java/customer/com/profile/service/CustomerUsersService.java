package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.logging.Logging;
import customer.com.profile.model.CustomerUsers;
import customer.com.profile.model.Vehicle;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.CustomerUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerUsersService {
    @Autowired
    CustomerUsersRepository customerUsersRepository;
    @Autowired
    CustomerRepository customerRepository;

    Logging logging = new Logging();

    public CustomerUsers createCustomerUsers(CustomerUsers customerUsers, Integer customerId) {
        logging.logger.info("Create user for customer: {}", customerId);
        Customer customer = customerRepository.findById(customerId).get();
        customerUsers.setCustomer(customer);
        logging.logger.info("Create user: {}", customerUsers.getUserName());
        return customerUsersRepository.save(customerUsers);
    }

    public CustomerUsers updateCustomerUsers(Integer id, List<Vehicle> vin) {
        logging.logger.info("Update userId: {}", id);
        CustomerUsers customerUsers = customerUsersRepository.findById(id).get();
        List<Vehicle> vehicle = customerUsers.getVehicle();
        vehicle.addAll(vin);
        customerUsers.setVehicle(vehicle);
        logging.logger.info("User is updated with vehicle: {}", vehicle);
        return customerUsersRepository.save(customerUsers);
    }

    public List<CustomerUsers> getCustomerUsers(Integer customerId) {
        logging.logger.info("Get customerId: {}", customerId);
        return customerUsersRepository.findCustomerUsersByCustomerId(customerId);
    }

    public List<CustomerUsers> findAllUsersOfAVehicle(String vin) {
        logging.logger.info("Find users that have driven vehicle: {}", vin);
        List<CustomerUsers> customerUsers = customerUsersRepository.findAllUsersOfVehicle(vin);
        logging.logger.info("List of users are: {}", customerUsers);
        return customerUsers;
    }

    public List<String> findAllUserNamesOfAVehicle(String vin) {
        logging.logger.info("Find userNames that have driven vehicle: {}", vin);
        List<CustomerUsers> customerUsers = customerUsersRepository.findAllUsersOfVehicle(vin);
        List<String> userName = new ArrayList<>();
        for (CustomerUsers user: customerUsers) {
            userName.add(user.getUserName());
        }
        logging.logger.info("List of userNames are: {}", userName);
        return userName;
    }

    public Integer findNoOfTimesForAVehicleByAUser(Integer userId, String vin) {
        logging.logger.info("The number of times user: {} has driven vehicle: {}", userId, vin);
        Integer count = customerUsersRepository.findNoOfTimesForAVehicleByAUser(userId, vin);
        logging.logger.info("The number of times is: {}", count);
        return count;
    }
}
