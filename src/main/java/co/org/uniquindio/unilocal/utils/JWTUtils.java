package co.org.uniquindio.unilocal.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    public String generarToken(String email, Map<String, Object> claims){
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                .signWith( getKey() )
                .compact();
    }

    public Jws<Claims> parseJwt(String jwsString) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith(getKey()).build();
        return jwtParser.parseSignedClaims(jwsString);
    }

    private SecretKey getKey(){
        String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretaKeyBytes = claveSecreta.getBytes();
        return Keys.hmacShaKeyFor(secretaKeyBytes);
    }
}
