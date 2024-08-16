package customer.com.profile.controller;

import customer.com.profile.dto.UsersOfAVehicleDto;
import customer.com.profile.mapper.UsersOfAVehicleMapper;
import customer.com.profile.model.Customer;
import customer.com.profile.model.CustomerUsers;
import customer.com.profile.model.Vehicle;
import customer.com.profile.service.CustomerUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerUsersController {
    @Autowired
    CustomerUsersService customerUsersService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user/customer/{customerId}")
    public ResponseEntity<CustomerUsers> createCustomerUsers(@RequestBody CustomerUsers customerUsers, @PathVariable Integer customerId){
        try{
            return new ResponseEntity<CustomerUsers>(customerUsersService.createCustomerUsers(customerUsers, customerId), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/user/{id}/vehicle/{vin}")
    public ResponseEntity<CustomerUsers> updateCustomerUsers(@PathVariable Integer id, @PathVariable List<Vehicle> vin){
        try{
            return new ResponseEntity<CustomerUsers>(customerUsersService.updateCustomerUsers(id, vin), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/vin/{vin}")
    public ResponseEntity<List<UsersOfAVehicleDto>> findAllUsersOfVehicle(@PathVariable String vin){
        try{
            List<CustomerUsers> customerUsers = customerUsersService.findAllUsersOfAVehicle(vin);
            return new ResponseEntity<>(new UsersOfAVehicleMapper().userOfAVehicle(customerUsers), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/username/vin/{vin}")
    public ResponseEntity<List<String>> findAllUserNameOfVehicle(@PathVariable String vin){
        try{
            return new ResponseEntity<>(customerUsersService.findAllUserNamesOfAVehicle(vin), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{userId}/vin/{vin}")
    public ResponseEntity<Integer> findNoOfTimesForAVehicleByAUser(@PathVariable Integer userId, @PathVariable String vin){
        try{
            return new ResponseEntity<>(customerUsersService.findNoOfTimesForAVehicleByAUser(userId, vin), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/customer/{customerId}")
    public ResponseEntity<List<CustomerUsers>> getCustomerUsers(@PathVariable Integer customerId){
        try {
            return new ResponseEntity<List<CustomerUsers>>(customerUsersService.getCustomerUsers(customerId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
