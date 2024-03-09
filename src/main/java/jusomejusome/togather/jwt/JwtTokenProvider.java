package jusomejusome.togather.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jusomejusome.togather.exception.custom.CustomException;
import jusomejusome.togather.exception.type.ErrorCode;
import jusomejusome.togather.user.domain.User;
import jusomejusome.togather.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserRepository userRepository;

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    private Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 3; //3일
//    private final Long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 14 * 1000L;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String generateAccessToken(User user){

        Claims claims = Jwts.claims().setSubject(user.getUserId().toString());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String resolveJwtToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Authentication getAuthenticationFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try {
            Long userId = Long.valueOf(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
            return new UsernamePasswordAuthenticationToken(user, "");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw new CustomException(ErrorCode.EXPIRED_TOKEN_EXCEPTION);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new CustomException(ErrorCode.INVALID_TOKEN_TYPE_EXCEPTION);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new CustomException(ErrorCode.INVALID_TOKEN_TYPE_EXCEPTION);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
            throw new CustomException(ErrorCode.INVALID_TOKEN_EXCEPTION);
        }
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e){
            return false;
        }
    }

}
