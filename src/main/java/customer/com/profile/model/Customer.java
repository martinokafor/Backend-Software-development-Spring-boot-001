package customer.com.profile.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "customer")
public class Customer extends AbstractEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String city;
    @Nullable
    private Integer noOfVehicle;

}
