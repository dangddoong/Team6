package middleProjects.com.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import middleProjects.com.exception.CustomException;
import middleProjects.com.exception.ExceptionStatus;
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
            log.info("ddddd");
            if (token != null) {
                jwtTokenProvider.validateTokenExpiration(token);
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (RuntimeException exception) {
            // 여긴 스프링 영역이 아니다.
            // 서블릿 리퀘스트, 디스패처 서블릿
            HttpServletResponse h = (HttpServletResponse) response;
            h.setStatus(401);
            exception.printStackTrace();
        }
    }


}