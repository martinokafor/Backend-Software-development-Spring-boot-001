package customer.com.profile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NonNull
    @NotEmpty
    @NotBlank
    private String vehicleName;
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String vin;
    @NonNull
    @NotEmpty
    @NotBlank
    private String model;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;
}
