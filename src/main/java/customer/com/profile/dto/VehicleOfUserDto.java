package customer.com.profile.dto;
import lombok.*;

@Data
@Getter
@Setter
public class VehicleOfUserDto{

    private String vehicleName;
    private String vin;
    private String model;


    public VehicleOfUserDto(
            String vehicleName,
            String vin,
            String model
    ){
        this.vehicleName=vehicleName;
        this.vin=vin;
        this.model=model;
    }

}
