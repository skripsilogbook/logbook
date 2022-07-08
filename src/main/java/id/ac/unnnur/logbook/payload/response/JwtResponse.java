package id.ac.unnnur.logbook.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String accessToken, Long userId, String username, String email, String role) {
        this.token = accessToken;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
