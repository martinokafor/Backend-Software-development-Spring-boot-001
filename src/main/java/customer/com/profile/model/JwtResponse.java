package customer.com.profile.model;

import java.io.Serializable;
import java.util.List;

public class JwtResponse  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String token;

    //private final String role;
    public JwtResponse(String token) {
        this.token = token;
        //this.role = role;
    }

    public String getToken() {
        return token;
    }
//    public String getRole() {
//        return role;
//    }
}

