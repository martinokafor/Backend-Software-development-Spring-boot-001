package customer.com.profile.service;

import customer.com.profile.model.Customer;
import customer.com.profile.model.Order;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Order createOrder(Order order, Integer customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        order.setCustomer(customer);
        return orderRepository.save(order);
    }
}
