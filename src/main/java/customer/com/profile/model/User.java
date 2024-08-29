package customer.com.profile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "USER_TBL")
public class User {
    @Id
    private Integer id;
    private String userName;
    private String password;
    private String role;
}
