package middleProjects.com.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;


@Setter
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    private long tokenValidTime = 1000L * 60 * 30; // 토큰 유효시
    private long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7; // refresh token 기한 7일
    private static final String BEARER_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    // 토큰 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now) // 발급 시간
                        .setExpiration(new Date(now.getTime() + tokenValidTime))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();
    }


    // refreshtoken 생성
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰으로 인증객체(Authentication) 얻기
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 이메일을 얻기 위해 실제로 토큰을 디코딩-> 지정된 secretekey를 이용해 인증 객체를 끌고올 수 있음.
    public String getMemberEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    // 이걸 가지고 헤더에서 토큰을 꺼내옴
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰의 유효성 검사
    public boolean validateTokenExpiration(String token) {
        try {
            log.info("문제야문제222"); // 여긴 탄다.
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token); //여기서 문제가 발생한다
            log.info("문제야문제"); // 이 놈은 터질리 없지~~
            return true;
        } catch (RuntimeException  ex) {
            log.info("감스트감스트");
            return false;
          // 다른 코드에서도 여기엔 false로 반환한다. -> 실패시?
        }
    }
}