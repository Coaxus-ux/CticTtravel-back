package ctictravel.ctictravel.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    public byte[] getKey() {
        byte[] secretKeyBytes = key.getBytes(StandardCharsets.UTF_8);

        return secretKeyBytes;
    }

    private String key = "J1K3VScAjJgK9BHLbmPG53qxaixzD7tsEb3r5WkAoBTQUmSTIKMR7r8fNL/IsMBK38yGm4VGdQ491IH3xJGtJw==";

    public long getTtlMillis() {
        return ttlMillis;
    }

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private String email;
    private String id;


    public String generateToken(String email, String id) {

        return Jwts.builder()
                .setSubject(email)
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + getTtlMillis()))
                .signWith(SignatureAlgorithm.HS512, getKey())
                .compact();
    }
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().getSubject();
    }
    public String getIdFromToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
