package customer.com.profile.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsersOfAVehicleDto {
    private String userName;
    private String password;

    public UsersOfAVehicleDto(
            String userName,
            String password
    ){
        this.userName=userName;
        this.password=password;
    }
}
