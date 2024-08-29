package customer.com.profile.service;

import customer.com.profile.dto.VehicleOfUserDto;
import customer.com.profile.model.Customer;
import customer.com.profile.model.Order;
import customer.com.profile.model.Vehicle;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.OrderRepository;
import customer.com.profile.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Vehicle> fetchAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle CreateVehicle(Vehicle vehicle, Integer customerId, Integer orderId) {
        Customer customer = customerRepository.findById(customerId).get();
        vehicle.setCustomer(customer);
        Order order = orderRepository.findById(orderId).get();
        vehicle.setOrder(order);
        Integer noOfVehicle;
        noOfVehicle = vehicleRepository.findByCustomerId(customerId).size();
        customer.setNoOfVehicle(noOfVehicle + 1);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicle(String vin) {
        return vehicleRepository.findById(vin).get();
    }

    public List<Vehicle> findAllByVehicleName(String vehicleName) {
        return vehicleRepository.findAllByVehicleName(vehicleName);
    }

    public List<Vehicle> findByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> findVehiclesByCustomerId(Integer customerId) {
        return vehicleRepository.findByCustomerId(customerId);
    }

    public Vehicle updateVehicle(Vehicle vehicle, String vin) {
        vehicle.setCustomer(vehicle.getCustomer());
        vehicleRepository.findById(vin).get();
        vehicle.setVehicleName(vehicle.getVehicleName());
        vehicle.setModel(vehicle.getModel());
        vehicle.setCreatedOn(vehicleRepository.findById(vin).get().getCreatedOn());
        return vehicleRepository.save(vehicle);
    }

    public Integer countVehiclesByCustomerId(Integer customerId) {
        return vehicleRepository.findByCustomerId(customerId).size();
    }

    public Integer deleteVehicleByVin(String vin) {
        vehicleRepository.deleteById(vin);
        return 0;
    }

    public List<String> findAllVINsOfAUser(Integer userId) {
        List<Vehicle> vehicle = vehicleRepository.findVehicleOfAUser(userId);
        List<String> vin = new ArrayList<>();
        for (Vehicle vin1: vehicle) {
            vin.add(vin1.getVin());
        }
        return vin;
    }

    public List<Vehicle> findAllVehiclesOfAUser(Integer userId) {
        return vehicleRepository.findVehicleOfAUser1(userId);
    }

}
