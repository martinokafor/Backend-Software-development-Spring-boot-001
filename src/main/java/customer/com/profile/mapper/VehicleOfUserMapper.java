package customer.com.profile.mapper;

import customer.com.profile.dto.VehicleOfUserDto;
import customer.com.profile.model.Vehicle;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleOfUserMapper {

    public List<VehicleOfUserDto> vehicleOfUser(List<Vehicle> vehicle) {
        List<VehicleOfUserDto> vehicleOfUserDtoList = new ArrayList<>();
        System.out.println(vehicle);
        vehicle.forEach((Vehicle vehicleOfUser) -> {
                    VehicleOfUserDto vehicleOfUserDto = new VehicleOfUserDto(
                    vehicleOfUser.getVehicleName(),
                    vehicleOfUser.getVin(),
                    vehicleOfUser.getModel()

                    );
                    vehicleOfUserDtoList.add(vehicleOfUserDto);
                }
        );
        System.out.println(vehicleOfUserDtoList);
        return vehicleOfUserDtoList;
    }
}
