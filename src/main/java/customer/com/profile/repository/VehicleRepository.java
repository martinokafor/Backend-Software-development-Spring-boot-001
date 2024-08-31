package customer.com.profile.repository;

import customer.com.profile.dto.VehicleOfUserDto;
import customer.com.profile.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByVehicleName(String vehicleName);

    @Query("select v from Vehicle v where v.model = ?1")
    List<Vehicle> findByModel(String model);

    @Query(value = "SELECT * FROM vehicle WHERE customer_id=?1", nativeQuery = true)
    List<Vehicle> findByCustomerId(Integer customerId);

    @Query(value = "SELECT vehicle.order_id, vehicle.model, vehicle.vehicle_name, vehicle.created_on," +
            " vehicle.updated_on, vehicle.vin, customer_users.customer_id  \n" +
            "FROM customerDatabase.vehicle \n" +
            "LEFT JOIN customerDatabase.customer_users ON customer_users.customer_id=vehicle.customer_id\n" +
            "WHERE customer_users.id=?1", nativeQuery = true)
    List<Vehicle> findVehicleOfAUser(Integer userId);

}
