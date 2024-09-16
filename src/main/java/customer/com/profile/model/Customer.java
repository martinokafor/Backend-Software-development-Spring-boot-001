package customer.com.profile.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.Audited;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Audited
@Table(name = "customer")
public class Customer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NonNull
    @NotEmpty
    @NotBlank
    private String customerName;
    @NonNull
    @NotEmpty
    @NotBlank
    @Size(min = 2, message = "city must be at least 2 characters")
    private String city;
    @Nullable
    private Integer noOfVehicle;

}
