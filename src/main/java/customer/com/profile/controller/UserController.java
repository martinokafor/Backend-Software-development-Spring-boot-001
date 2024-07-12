package customer.com.profile.controller;

import customer.com.profile.model.JwtResponse;
import customer.com.profile.model.User;
import customer.com.profile.model.UserDetail;
import customer.com.profile.service.CustomUserDetailsService;
import customer.com.profile.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody User user) {
        UserDetail userDetail = customUserDetailsService.loadUserByUsername(user.getUserName());
            if (Objects.equals(userDetail.getPassword(), user.getPassword())){
                System.out.println(userDetail.getUsername() + " "+ "exist in the database");
            }
        return new JwtResponse(jwtUtil.generateToken(user.getUserName(), user.getRole()));
    }
}
