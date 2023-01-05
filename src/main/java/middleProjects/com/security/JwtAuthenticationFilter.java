package middleProjects.com.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.exception.mymymymyException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (token != null && jwtTokenProvider.validateTokenExpiration(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (RuntimeException e) {
            // 확인한 첫번째 -> 이건 예외가 아니다?  예외는 다른 곳에서 터진다
            log.info("문제인문제인");
            HttpServletResponse realResponse = (HttpServletResponse) response;
//            log.error("e = {}", e.getMessage());
            realResponse.setContentType("application/json;charset=UTF-8");
            realResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new mymymymyException("ddd");
//            JSONObject responseJson = new JSONObject();
//            responseJson.put("message", e.getMessage());
//            responseJson.put("code", 400);
//
//            realResponse.getWriter().print(responseJson);

        }
    }


}