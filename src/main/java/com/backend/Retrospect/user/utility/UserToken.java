package com.backend.Retrospect.user.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
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

    private RSAPublicKey getPublicKey() throws Exception {
        String publicKeyPEM = "6Ag2LrcePlgnNimT10o2fB4P0Lu8mFAgUMEfEAcMf8I";

        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    public String deToken(String token) {
        try {
            RSAPublicKey publicKey = getPublicKey();
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return jwt.getClaim("userName").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
