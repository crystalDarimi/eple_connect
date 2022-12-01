package com.crystal.eple.security;


import com.crystal.eple.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final UserDetailsService userDetailsService;
    private  String secretKey = "NMA8JPctFuna59f5";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Date expiryDate = Date.from( //기한은 1일 뒤로 설정
                Instant.now().plus(2, ChronoUnit.DAYS));
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(new Date()) //iat
                .setExpiration(expiryDate) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }



    public String create(UserEntity userEntity) { //JWT 라이브러리를 이용해 JWT 토큰 생성 (임의로 지정한 시크릿키 사용)
        Date expiryDate = Date.from( //기한은 1일 뒤로 설정
                Instant.now().plus(2, ChronoUnit.DAYS));
        Claims claims = Jwts.claims().setSubject(userEntity.getId());
        claims.put("roles",userEntity.getRoles());


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
                .signWith(SignatureAlgorithm.HS512, secretKey) //header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                //payload에 들어갈 내용
                .setClaims(claims) //sub
                .setIssuer("eple app") //iss
                .setIssuedAt(new Date()) //iat
                .setExpiration(expiryDate) //exp
                .compact();
    }


        public String validateAndGetUserId(String token){
            //parseClaimsJws 메서드가 Base 64 디코딩 및 파싱,
            // 즉 헤더와 헤이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명 후 token의 서명과 비교
            //위조되지 않았다면 페이로드 (Claims) 리턴, 위조라면 예외를 날림
            //그 중 우리는 userId가 필요하므로 getBody를 부른다
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

        return claims.getSubject();

    }


    public Claims validateAndGetClaims(String token){
        //parseClaimsJws 메서드가 Base 64 디코딩 및 파싱,
        // 즉 헤더와 헤이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명 후 token의 서명과 비교
        //위조되지 않았다면 페이로드 (Claims) 리턴, 위조라면 예외를 날림
        //그 중 우리는 userId가 필요하므로 getBody를 부른다
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();


        return claims;
    }

}
    /*
    public boolean validateToken(String token) {
        log.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            log.info("[validateToken] 토큰 유효 체크 완료");
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }


     */



