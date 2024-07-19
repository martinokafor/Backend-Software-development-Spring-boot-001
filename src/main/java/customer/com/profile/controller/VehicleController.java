package customer.com.profile.controller;

import customer.com.profile.config.ApiError;
import customer.com.profile.model.Vehicle;
import customer.com.profile.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @Operation(
            description = "Fetch all vehicles in the database",
            summary = "Fetch all vehicles",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicles")
    public List<Vehicle> fetchAllVehicle() {
        try{
            return vehicleService.fetchAllVehicles();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Operation(
            description = "Fetch a vehicle by vin from the database",
            summary = "Fetch a vehicle by vin",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicle/vin/{vin}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vin){
        try {
            return new ResponseEntity<Vehicle>(vehicleService.getVehicle(vin), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Create a vehicle for a customer using the customerId",
            summary = "Create a vehicle for a customer",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "201"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @PostMapping("/vehicle/customer/{customerId}/order/{orderId}")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable Integer customerId, @PathVariable Integer orderId){
        try{
            return new ResponseEntity<Vehicle>(vehicleService.CreateVehicle(vehicle, customerId, orderId), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
            description = "Update a vehicle using vin",
            summary = "Update a vehicle using vin to fetch the vehicle",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @PutMapping("/vehicle/{vin}")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable String vin){
        try{
            return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle, vin), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
            description = "Fetch a vehicle by the vehicleName from the database",
            summary = "Fetch a vehicle by the vehicleName",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicle/vehicle_name/{vehicleName}")
    public ResponseEntity<List<Vehicle>> findVehicleByName(@PathVariable String vehicleName){
        try {
            return new ResponseEntity<>(vehicleService.findAllByVehicleName(vehicleName), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Fetch a vehicle by model from the database",
            summary = "Fetch a vehicle by model",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicle/model/{model}")
    public ResponseEntity<List<Vehicle>> findByModel(@PathVariable String model){
        try {
            return new ResponseEntity<>(vehicleService.findByModel(model), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Fetch a vehicle by the customerId from the database",
            summary = "Fetch a vehicle by the customerId",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicles/customer_id/{customerId}")
    public ResponseEntity<List<Vehicle>> findVehiclesByCustomerId(@PathVariable Integer customerId){
        try {
            return new ResponseEntity<>(vehicleService.findVehiclesByCustomerId(customerId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Count number of vehicles of a customer in the database",
            summary = "Count number of vehicles of a  customer",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @GetMapping("/vehicles/{customerId}")
    public ResponseEntity<Integer> countVehiclesByCustomerId(@PathVariable Integer customerId){
        try {
            return new ResponseEntity<>(vehicleService.countVehiclesByCustomerId(customerId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Delete a vehicle by vin in the database",
            summary = "Delete a vehicle by vin",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))

                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiError.class))
                    ),
            }
    )
    @DeleteMapping("/vehicle/{vin}")
    public void deleteCustomer(@PathVariable String vin){
        try {
            new ResponseEntity<>(vehicleService.deleteVehicleByVin(vin), HttpStatus.OK);
        }catch (Exception e){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
