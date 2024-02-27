package customer.com.profile.controller;

import customer.com.profile.model.Vehicle;
import customer.com.profile.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<Vehicle> fetchAllVehicle(){
        return vehicleService.fetchAllVehicles();
    }

    @GetMapping("/vehicle/vin/{vin}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vin){
        try {
            return new ResponseEntity<Vehicle>(vehicleService.getVehicle(vin), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehicle/customer/{customerId}")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle, @PathVariable Integer customerId){
        try{
            return new ResponseEntity<Vehicle>(vehicleService.CreateVehicle(vehicle, customerId), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/vehicle/vehicle_name/{vehicleName}")
    public List<Vehicle> findVehicleByName(@PathVariable String vehicleName){
        return vehicleService.findAllByVehicleName(vehicleName);
    }
    @GetMapping("/vehicle/model/{model}")
    public List<Vehicle> findByModel(@PathVariable String model){
        return vehicleService.findByModel(model);
    }

    @GetMapping("/vehicles/customer_id/{customerId}")
    public List<Vehicle> findVehiclesByCustomerId(@PathVariable Integer customerId){
        return vehicleService.findVehiclesByCustomerId(customerId);
    }

    @GetMapping("/vehicles/{customerId}")
    public Integer countVehiclesByCustomerId(@PathVariable Integer customerId){
        return vehicleService.countVehiclesByCustomerId(customerId);
    }

    @DeleteMapping("/vehicle/{vin}")
    public void deleteCustomer(@PathVariable String vin){
        try {
            vehicleService.deleteVehicleByVin(vin);
            new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
