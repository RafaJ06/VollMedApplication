package VollMed.Infra.Security;


import VollMed.Domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    Algorithm algorithm;
    private final String Issuer = "VollMed API";

    public String CreateToken(User user) {
        this.algorithm = Algorithm.HMAC256(this.secret);

        try {
            return JWT.create().withIssuer("VollMed API").withSubject(user.getUsername()).withExpiresAt(this.ExpirationDate()).sign(this.algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("An error occurred", exception);
        }
    }

    private Instant ExpirationDate() {
        return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-04:00"));
    }

    public String getSubject(String tokenJwt) {
        try {
            return JWT.require(this.algorithm).withIssuer("VollMed API").build().verify(tokenJwt).getSubject();
        } catch (JWTVerificationException var3) {
            throw new RuntimeException("Invalid Token");
        }
    }
}

