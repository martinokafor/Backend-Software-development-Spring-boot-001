package customer.com.profile.service;

import customer.com.profile.model.User;
import customer.com.profile.model.UserDetail;
import customer.com.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new UserDetail(user);
        //return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public String getUserRole(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        //List<String> grantedAuthoritiesAsStrings = new ArrayList<>();
        return user.getRole();
    }


}
