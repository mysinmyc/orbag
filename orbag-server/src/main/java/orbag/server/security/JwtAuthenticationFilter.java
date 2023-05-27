package orbag.server.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends GenericFilterBean
{

    public static String TOKEN_HEADER="Authorization";
    public static String TOKEN_PREFIX="Bearer ";

    public static String COOKIE="JWT_SSO";

    @Autowired
    AuthenticationService authenticationService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            String token = extractTokenFromRequest((HttpServletRequest) request);
            if (token != null && !token.isEmpty()) {
                try {
                    Authentication authentication = authenticationService.loginByToken(token.replace(TOKEN_PREFIX, ""));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (OrbagSecurityException e) {
                    ((HttpServletResponse) response).setStatus(403);
                    return;
                }
            } else {
                String tokenFromCookie=extactTokenFromCookie((HttpServletRequest) request);
                if (tokenFromCookie!=null && ! tokenFromCookie.isEmpty()){
                    try {
                        Authentication authentication = authenticationService.loginByToken(tokenFromCookie);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } catch (OrbagSecurityException e) {
                    }
                }
            }

            chain.doFilter(request,response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String tokenHeader=request.getHeader(TOKEN_HEADER);
        if ( tokenHeader !=null && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return tokenHeader.replace(TOKEN_PREFIX,"");
        }
        return null;
    }

    private String extactTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies =request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
