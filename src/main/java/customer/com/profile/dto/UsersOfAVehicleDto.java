package customer.com.profile.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsersOfAVehicleDto {
    private String userName;
    private String role;

    public UsersOfAVehicleDto(
            String userName,
            String role
    ) {
        this.userName = userName;
        this.role = role;
    }
}
