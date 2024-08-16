package customer.com.profile.mapper;

import customer.com.profile.dto.UsersOfAVehicleDto;
import customer.com.profile.model.CustomerUsers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersOfAVehicleMapper {
    public List<UsersOfAVehicleDto> userOfAVehicle(List<CustomerUsers> customerUsers){
        List<UsersOfAVehicleDto> usersOfAVehicleList = new ArrayList<>();
        for (CustomerUsers user: customerUsers){
            UsersOfAVehicleDto usersOfAVehicleDto = new UsersOfAVehicleDto(
                    user.getUserName(),
                    user.getPassword()
            );
            usersOfAVehicleList.add(usersOfAVehicleDto);
        }
        return usersOfAVehicleList;
    }
}
