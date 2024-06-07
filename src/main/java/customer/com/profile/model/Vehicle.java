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
public class Vehicle extends AbstractEntity{
    private String vehicleName;
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String vin;
    @NonNull
    private String model;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;
}
