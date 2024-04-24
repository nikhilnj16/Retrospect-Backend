package com.backend.Retrospect.user.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static javax.crypto.Cipher.SECRET_KEY;
@Component
public class UserToken {
    private static final String SECRET_KEY = "ndvushdvyusdjcdfdcdhfyu";
    public String createToken(String userName){

        return JWT.create()
                .withClaim("userName", userName)
                .sign(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())));
    }

    public String decodeToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())))
                    .build()
                    .verify(token)
                    .getClaim("userName")
                    .asString();
        } catch (Exception ex){
            return null;
        }
    }
}
