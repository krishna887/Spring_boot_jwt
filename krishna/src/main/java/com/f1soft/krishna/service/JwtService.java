package com.f1soft.krishna.service;

import com.f1soft.krishna.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {
    private final  String SECRET_KEY="4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    public boolean isValid(String token, UserDetails user){
        String username=extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateToken(AppUser appUser){

        String token = Jwts.builder()
                .subject(appUser.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*2))//for two minute
                .signWith(getSigninKey())
                .compact();
        return  token;
    }

    public SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

//    frome here I am tried to implement jose
//    JWTClaimsSet claims = new JWTClaimsSet.Builder()
//            .claim("email", "sanjay@example.com")
//            .claim("name", "Sanjay Patel")
//            .build();
//
//    Payload payload = new Payload(claims.toJSONObject());
//
//
//    JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
//
//    String secret = “841D8A6C80CBA4FCAD32D5367C18C53B”;
//    byte[] secretKey = secret.getBytes();
//     encrypter = new DirectEncrypter(secretKey);
//
//    JWEObject jweObject = new JWEObject(header, payload);
//jweObject.encrypt(encrypter);
//    String token = jweObject.serialize();

//    public static ECKey generateSecret() {
//        com.nimbusds.jose.jwk.ECKey ecKey = null;
//        try {
//            ecKey = new ECKeyGenerator(Curve.P_256K)
//                    .keyUse(KeyUse.SIGNATURE) // indicate the intended use of the key
//                    .keyID(UUID.randomUUID().toString()) // give the key a unique ID
//                    .generate(); // generate the EC key
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ecKey;
//    }
}