package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("id",1);
        claims.put("username","1234");
        String token = JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))
                .sign(Algorithm.HMAC256("asdfghjk"));
        System.out.println(token);
    }

//    @Test
//    public void testParse() {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXV" +
//                "CJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2V" +
//                "ybmFtZSI6IjEyMzQifSwiZXhw" +
//                "IjoxNzIxNzg2MTM0fQ.hyi0lTbBoBI1nOC20d4bRB" +
//                "C40BkOTnJcOA3_VbvwXac";
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("asdfghjk")).build();
//        DecodedJWT decodedJWT = jwtVerifier.verify(token);
//        Map<String, Claim> claimMap = decodedJWT.getClaims();
//        System.out.println(claimMap.get("user"));
//    }
    }
