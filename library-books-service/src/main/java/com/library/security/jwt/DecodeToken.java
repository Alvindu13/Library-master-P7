package com.library.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public class DecodeToken {

    public String decodeUsername(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        System.out.println("******************* Token = " + jwtToken);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
        String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
        System.out.println("******************* TOKEN SANS PREFIX = " + jwt);
        DecodedJWT decodedJWT = verifier.verify(jwtToken.substring(SecurityParams.HEADER_PREFIX.length()));
        String username = decodedJWT.getSubject();


        return username;
    }
}
