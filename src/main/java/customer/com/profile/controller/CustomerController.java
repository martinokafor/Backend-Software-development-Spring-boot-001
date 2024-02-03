package customer.com.profile.controller;

import customer.com.profile.model.Customer;
import customer.com.profile.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> fetchAllCustomer(){
        return customerService.fetchAllCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId){
        try {
            return new ResponseEntity<Customer>(customerService.getCustomer(customerId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try{
            return new ResponseEntity<Customer>(customerService.CreateCustomer(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/customer/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId){
        return customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId){
        try {
            customerService.deleteCustomer(customerId);
            new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/city/{city}")
    public ResponseEntity<List<Customer>> findByCity(@PathVariable String city){
        try {
            return new ResponseEntity<List<Customer>>(customerService.findByCity(city), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/customer_name/{CustomerName}")
    public ResponseEntity<List<Customer>> findByCustomerName(@PathVariable String CustomerName){
        try {
            return new ResponseEntity<List<Customer>>(customerService.findByCustomerName(CustomerName), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
