package com.crystal.eple.security;


import com.crystal.eple.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "NMA8JPctFuna59f5";
    public String create(UserEntity userEntity) { //JWT 라이브러리를 이용해 JWT 토큰 생성 (임의로 지정한 시크릿키 사용)
        Date expiryDate = Date.from( //기한은 1일 뒤로 설정
                Instant.now().plus(1, ChronoUnit.DAYS));

        	/*
		{ // header
		  "alg":"HS512"
		}.
		{ // payload
		  "sub":"40288093784915d201784916a40c0001",
		  "iss": "demo app",
		  "iat":1595733657,
		  "exp":1596597657
		}.
		// SECRET_KEY를 이용해 서명한 부분
		Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */
        // JWT Token 생성
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) //header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                //payload에 들어갈 내용
                .setSubject(userEntity.getId()) //sub
                .setIssuer("eple app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }


        public String validateAndGetUserId(String token){ //토큰 디코딩, 파싱 및 위조여부 확인 후 subject(유저 아이디) 리턴
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

        return claims.getSubject();

    }

}
