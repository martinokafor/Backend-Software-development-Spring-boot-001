package customer.com.profile.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Audited
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderId;
    String brand;
    String price;
    String currency;
    Boolean consent;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;
}
