package customer.com.profile.controller;

import customer.com.profile.model.CustomerUsers;
import customer.com.profile.model.Order;
import customer.com.profile.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order/customer/{customerId}")
    public ResponseEntity<Order> createCustomerUsers(@RequestBody Order order, @PathVariable Integer customerId){
        try{
            return new ResponseEntity<Order>(orderService.createOrder(order, customerId), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
