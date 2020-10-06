package com.ren.renzen.Security;

import com.ren.renzen.DomainObjects.ProfileDO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ren.renzen.additional.KEYS.EXPIRATION_TIME;
import static com.ren.renzen.additional.KEYS.SECRET;

public class JwtTokenProvider {

    //Generate the token

    public String generateToken(Authentication authentication){
        ProfileDO profileDO = (ProfileDO) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = profileDO.get_id().toHexString(); //???

        Map<String,Object> claims = new HashMap<>();

        //information to include in token to get when you decode token
        //can throw in roles
        claims.put("id",userId);
        claims.put("username",profileDO.getUsername());
        
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact()
                ;
    }

    //Validate the token

    //Get user Id from token
}
