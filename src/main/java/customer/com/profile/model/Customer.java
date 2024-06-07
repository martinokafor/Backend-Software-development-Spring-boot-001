package customer.com.profile.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

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
    @NonNull
    @NotEmpty
    @NotBlank
    private String customerName;
    @NonNull
    @NotEmpty
    @NotBlank
    private String city;
    @Nullable
    private Integer noOfVehicle;

}
