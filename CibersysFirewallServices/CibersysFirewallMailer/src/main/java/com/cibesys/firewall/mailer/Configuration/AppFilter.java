package com.cibesys.firewall.mailer.Configuration;


import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by AKDESK04 on 3/28/2017.
 */
public class AppFilter extends OncePerRequestFilter {

    @Value("${cibersys.token.header}")
    private String tokenHeader;

    @Value("${cibersys.services.mailer.services}")
    private String services;

    @Value("${cibersys.token.secret}")
    private String secret;

    @Value("${cibersys.token.expiration}")
    private Long expiration;


    private TokenUtils tokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        AntPathMatcher urlMatch = new AntPathMatcher();
        response.addHeader("Access-Control-Allow-Headers",tokenHeader);
        response.addHeader("Access-Control-Expose-Headers",tokenHeader);
        if (urlMatch.match(services + "**",
                request.getRequestURI().substring(request.getContextPath().length()))) {
            if (request.getHeader(tokenHeader) == null) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
            } else {
                tokenUtils = new TokenUtils(secret,expiration);
                UserDTO userToken = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
                if (userToken == null) {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
                } else {
                    if (userToken.getUserName().trim().equals("internal@cibersys") && userToken.getPassword().trim().equals("internal@cibersys")){
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                        authorities.add(new SimpleGrantedAuthority("ADMIN"));
                        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userToken.getUserName(), userToken.getPassword(), authorities);
                        SecurityContextHolder.getContext().setAuthentication(authRequest);
                        filterChain.doFilter(request, response);
                    }else {
                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El usuario no es valido.");
                    }
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
