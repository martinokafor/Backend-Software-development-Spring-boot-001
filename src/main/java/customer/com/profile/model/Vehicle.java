package customer.com.profile.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "vehicle")
@Entity
public class Vehicle {
    private String vehicleName;
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String vin;
    private String model;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;
}
