package customer.com.profile.repository;

import customer.com.profile.model.CustomerUsers;
import customer.com.profile.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerUsersRepository extends JpaRepository<CustomerUsers, Integer> {
    @Query(value = "SELECT * FROM customer_users WHERE customer_id=?1", nativeQuery = true)
    List<CustomerUsers> findCustomerUsersByCustomerId(Integer customerId);

    @Query(value = "SELECT customer_users.id, customer_users.password, customer_users.user_name, customer_users.customer_id, customer_users.role" +
            " FROM customerDatabase.customer_users \n" +
            "LEFT JOIN customerDatabase.user_vehicle ON customer_users.id=user_vehicle.user_id\n" +
            "WHERE user_vehicle.vehicle_vin=?1", nativeQuery = true)
    List<CustomerUsers> findAllUsersOfVehicle(String vin);

    @Query(value = "SELECT COUNT(*) FROM customerDatabase.customer_users \n" +
            "LEFT JOIN customerDatabase.user_vehicle ON customer_users.id=user_vehicle.user_id\n" +
            "WHERE customer_users.id=:userId AND user_vehicle.vehicle_vin=:vin",
            nativeQuery = true)
    Integer findNoOfTimesForAVehicleByAUser(Integer userId, String vin);
}
