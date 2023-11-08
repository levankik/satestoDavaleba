package softlab.satestodavalebaback.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =  "6e756148487758434e4e77676b4d626f4e72313433626356573268646c62795043634b4832506c42636c552f376e535033707135477437494c756536374234765250757947634359742f4b6d424b6a426d654e6a737a326d787575796b4f4968416a6976466f596436416e4c4348796d382f4f34504275356852554e413147672f58656f4b7346366e3752466633746e68443939722b6d71425468655133523043532b362b2f37634d7163794375306b5a6d4532386d625553334a7463643257536655354858474a61563661474465773932544d5a724f457970616a514a434b342f675a31756e4235632f70417158392f57566335552b33576a536a63655564596c79464f4b716d51386848666d6c6550707632536f596d79305474532f66776a7470356f2b48574d326850392f56345978384c536b334879365a70394b756d36493257302b514e497237516a35746a4476616e623733496853424778516e6e5372354e66496d4f4c2f733d";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSighInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSighInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSighInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
