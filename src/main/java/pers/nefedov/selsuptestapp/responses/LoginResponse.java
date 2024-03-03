package pers.nefedov.selsuptestapp.responses;

import lombok.*;

@Getter
@Setter

public class LoginResponse {
    private String token;
    private long expiresIn;
}
