package customer.com.profile.controller;

import customer.com.profile.config.ApiError;
import customer.com.profile.model.Customer;
import customer.com.profile.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

  @Operation(
          description = "Fetch all commercial customers in the database",
          summary = "Fetch all commercial customers",
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
    @GetMapping("/customers")
    public List<Customer> fetchAllCustomer(){
        return customerService.fetchAllCustomers();
    }

    @Operation(
            description = "Fetch a customer in the database using customerId",
            summary = "Fetch a customer using customerId",
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
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId){
        try {
            return new ResponseEntity<Customer>(customerService.getCustomer(customerId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Create a customer in the database",
            summary = "Create a customer",
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
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try{
            return new ResponseEntity<Customer>(customerService.CreateCustomer(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
            description = "Update a customer in the database",
            summary = "Update a customer",
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
    @PutMapping("/customer/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId){
        return customerService.updateCustomer(customer, customerId);
    }

    @Operation(
            description = "Delete a customer in the database",
            summary = "Delete a customer",
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
    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId){
        try {
            customerService.deleteCustomer(customerId);
            new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Fetch a customer by city of the customer",
            summary = "Fetch a customer by city",
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
    @GetMapping("/customer/city/{city}")
    public ResponseEntity<List<Customer>> findByCity(@PathVariable String city){
        try {
            return new ResponseEntity<List<Customer>>(customerService.findByCity(city), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Fetch a customer by customerName",
            summary = "Fetch a customer by customerName",
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
    @GetMapping("/customer/customer_name/{CustomerName}")
    public ResponseEntity<List<Customer>> findByCustomerName(@PathVariable String CustomerName){
        try {
            return new ResponseEntity<List<Customer>>(customerService.findByCustomerName(CustomerName), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
