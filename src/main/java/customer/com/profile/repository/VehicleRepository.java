package customer.com.profile.repository;

import customer.com.profile.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByVehicleName(String vehicleName);

    @Query("select v from Vehicle v where v.model = ?1")
    List<Vehicle> findByModel(String model);

    @Query(value="SELECT * FROM vehicle WHERE customer_id=?1", nativeQuery = true)
    List<Vehicle> findByCustomerId(Integer customerId);

}
