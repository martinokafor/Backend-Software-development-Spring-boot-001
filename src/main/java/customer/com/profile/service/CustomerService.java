package customer.com.profile.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import customer.com.profile.model.Customer;
import customer.com.profile.repository.CustomerRepository;
import customer.com.profile.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import java.util.Arrays;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.nio.file.Files;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    WebClient webClient;

    private final String FILE_PATH = "C:\\E2E\\Backend-Software-development-Spring-boot-001\\";

    public Page<Customer> fetchAllCustomers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("customerName").ascending());
        return customerRepository.findAll(pageable);
    }

    public Customer CreateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Integer customerId) {
        return customerRepository.findById(customerId).get();
    }

    public Customer updateCustomer(Customer customer, Integer customerId) {
        customerRepository.findById(customerId).get();
        customer.setCustomerId(customer.getCustomerId());
        customer.setCustomerName(customer.getCustomerName());
        customer.setCity(customer.getCity());
        int noOfVehicle = vehicleRepository.findByCustomerId(customerId).size();
        customer.setNoOfVehicle(noOfVehicle);
        customer.setCreatedOn(customerRepository.findById(customerId).get().getCreatedOn());
        customerRepository.save(customer);
        return customer;
    }

    public double deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
        return 0;
    }

    public List<Customer> findByCity(String city) {
        return customerRepository.findByCity(city);
    }

    public List<Customer> findByCustomerName(String customerName) {
        return customerRepository.findByCustomerName(customerName);
    }

    public void uploadCustomer(MultipartFile file) throws IOException, CsvException, NumberFormatException {
        CSVReader reader = new CSVReader(new FileReader(FILE_PATH + file.getOriginalFilename()));
        reader.skip(1);
        List<String[]> rows = reader.readAll();
        for (String[] row: rows) {
            Customer customer = new Customer();
            customer.setCity(row[0]);
            customer.setCreatedOn(Instant.parse(row[1]));
            customer.setCustomerId(Integer.valueOf(row[2]));
            customer.setCustomerName(row[3]);
            if (row[4].isEmpty()) {
                customer.setNoOfVehicle(null);
            } else {
                customer.setNoOfVehicle(Integer.valueOf(row[4]));
            }

            customer.setUpdatedOn(Instant.parse(row[5]));
            customerRepository.save(customer);
        }
    }

    public Object[] consumeCustomerProducers() {

        Object[] customer = webClient.get()
                .uri("http://localhost:8084/customer_producers")
                .retrieve()
                .bodyToMono(Object[].class)
                .block();

        System.out.println(Arrays.stream((customer)).toList());
        System.out.println("request was sent");
        return customer;
    }

    public Object produceCustomerProducers(Customer customer) {

        Object customerResponse = webClient.post()
                .uri("http://localhost:8084/customer_producer")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customer))
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        System.out.println(customerResponse);
        System.out.println("request was sent");
        return customerResponse;
    }
}
