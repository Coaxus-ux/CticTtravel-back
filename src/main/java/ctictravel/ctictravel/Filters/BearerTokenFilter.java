package ctictravel.ctictravel.Filters;


import ctictravel.ctictravel.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;

@Component
public class BearerTokenFilter extends OncePerRequestFilter {
    private  boolean isProtected(String path){
        return path.equals("/admins/update") || path.startsWith("/api/v1/");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String path = new UrlPathHelper().getPathWithinApplication(request);
        if(!isProtected(path)){
            filterChain.doFilter(request, response);
            return;
        }
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            JwtUtil jwtUtil = new JwtUtil();
            boolean isAuthorized = jwtUtil.validateToken(jwt);

            if (isAuthorized)
                request.setAttribute("jwt", jwt);
            else {
                response.setStatus(401);
                response.getWriter().write("Unauthorized");
                return;
            }
        }else{
            response.setStatus(401);
            response.getWriter().write("Unauthorized");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
