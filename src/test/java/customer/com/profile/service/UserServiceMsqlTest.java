package customer.com.profile.service;

import customer.com.profile.config.CustomerConfig;
import customer.com.profile.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Transactional
@SpringBootTest
public class UserServiceMsqlTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerUsersService customerUsersService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    CustomerConfig customerConfig;

    private final String FIRST_CUSTOMER_NAME = "Martin";
    private final String SECOND_CUSTOMER_NAME = "Okafor";
    private final String FIRST_CUSTOMER_CITY = "dresden";
    private final String SECOND_CUSTOMER_CITY = "dresden";

    Customer firstCustomer = Customer.builder().
            customerName(FIRST_CUSTOMER_NAME).
            city(FIRST_CUSTOMER_CITY).
            build();
    Customer secondCustomer = Customer.builder().
            customerName(SECOND_CUSTOMER_NAME).
            city(SECOND_CUSTOMER_CITY).
            build();

    @BeforeEach
    void setUp() {
        customerService.CreateCustomer(firstCustomer);
        customerService.CreateCustomer(secondCustomer);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser(){
        // Order
        Order orderOne = Order.builder().brand("VW").
                price("10000").
                currency("Euro").
                consent(true).
                customer(firstCustomer).build();
        Order orderTwo = Order.builder().brand("Mercedes").
                price("1000").
                currency("Euro").
                consent(true).
                customer(firstCustomer).build();

        Order createdOrderOne = orderService.createOrder(orderOne, firstCustomer.getCustomerId());
        Order createdOrderTwo = orderService.createOrder(orderTwo, firstCustomer.getCustomerId());

        // Vehicle
        Vehicle vehicleOne = Vehicle.builder().
                vehicleName("Volkswagen").
                model("POLO").
                order(createdOrderOne).
                customer(firstCustomer).build();
        Vehicle vehicleTwo = Vehicle.builder().
                vehicleName("Volkswagen").
                model("UP").
                order(createdOrderOne).
                customer(firstCustomer).build();
        Vehicle vehicleThree = Vehicle.builder().
                vehicleName("Volkswagen").
                model("PHATFARM").
                order(createdOrderOne).
                customer(firstCustomer).build();
        Vehicle vehicleFour = Vehicle.builder().
                vehicleName("Volkswagen").
                model("Passat").
                order(createdOrderOne).
                customer(firstCustomer).build();
        vehicleService.CreateVehicle(vehicleOne, firstCustomer.getCustomerId(), orderOne.getOrderId());
        vehicleService.CreateVehicle(vehicleTwo, firstCustomer.getCustomerId(), orderOne.getOrderId());
        vehicleService.CreateVehicle(vehicleThree, firstCustomer.getCustomerId(), orderOne.getOrderId());
        vehicleService.CreateVehicle(vehicleFour, firstCustomer.getCustomerId(), orderOne.getOrderId());

        Vehicle vehicleOneOrderTwo = Vehicle.builder().
                vehicleName("Mercedes").
                model("benz").
                order(createdOrderTwo).
                customer(firstCustomer).build();
        Vehicle vehicleTwoOrderTwo = Vehicle.builder().
                vehicleName("Mercedes").
                model("coupe").
                order(createdOrderTwo).
                customer(firstCustomer).build();
        Vehicle vehicleThreeOrderTwo = Vehicle.builder().
                vehicleName("Mercedes").
                model("beattle").
                order(createdOrderTwo).
                customer(firstCustomer).build();
        vehicleService.CreateVehicle(vehicleOneOrderTwo, firstCustomer.getCustomerId(), orderTwo.getOrderId());
        vehicleService.CreateVehicle(vehicleTwoOrderTwo, firstCustomer.getCustomerId(), orderTwo.getOrderId());
        vehicleService.CreateVehicle(vehicleThreeOrderTwo, firstCustomer.getCustomerId(), orderTwo.getOrderId());

        // User
        CustomerUsers customerUsersOne = CustomerUsers.builder().
                userName("AAA").
                role("user").
                password("aaaaaaa").
                build();
        CustomerUsers customerUsersTwo = CustomerUsers.builder().
                userName("ABC").
                role("user").
                password("xxxxxxx").
                build();
        CustomerUsers customerUsersThree = CustomerUsers.builder().
                userName("BCA").
                role("user").
                password("xxxxxxx").
                build();
        customerUsersService.createCustomerUsers(customerUsersOne, firstCustomer.getCustomerId());
        customerUsersService.createCustomerUsers(customerUsersTwo, firstCustomer.getCustomerId());
        customerUsersService.createCustomerUsers(customerUsersThree, firstCustomer.getCustomerId());

        // update users
        List <Vehicle> vehicleCustomerUserOne = new ArrayList<>(Arrays.asList(vehicleOne, vehicleTwo, vehicleThree));;
        customerUsersService.updateCustomerUsers(customerUsersOne.getId(), vehicleCustomerUserOne);
        List <Vehicle> vehicleCustomerUserTwo = new ArrayList<>(Arrays.asList(vehicleOne, vehicleTwo, vehicleThree, vehicleFour));;
        customerUsersService.updateCustomerUsers(customerUsersTwo.getId(), vehicleCustomerUserTwo);
        List <Vehicle> vehicleCustomerUserThree = new ArrayList<>(Arrays.asList(vehicleOne, vehicleTwo, vehicleThree, vehicleFour));;
        customerUsersService.updateCustomerUsers(customerUsersThree.getId(), vehicleCustomerUserThree);

        List <Vehicle> vehicleCustomerUserOne1 = new ArrayList<>(Arrays.asList(vehicleOneOrderTwo, vehicleTwo, vehicleThree));;
        customerUsersService.updateCustomerUsers(customerUsersOne.getId(), vehicleCustomerUserOne1);
        List <Vehicle> vehicleCustomerUserTwo1 = new ArrayList<>(Arrays.asList(vehicleOneOrderTwo, vehicleTwo, vehicleThree, vehicleFour));;
        customerUsersService.updateCustomerUsers(customerUsersTwo.getId(), vehicleCustomerUserTwo1);
        List <Vehicle> vehicleCustomerUserThree1 = new ArrayList<>(Arrays.asList(vehicleOneOrderTwo, vehicleTwo, vehicleThree, vehicleFour));;
        customerUsersService.updateCustomerUsers(customerUsersThree.getId(), vehicleCustomerUserThree1);
    }
}
