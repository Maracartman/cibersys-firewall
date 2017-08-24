package com.cibersys.firewall.Configuration;


import com.cibersys.firewall.security.ApplicationSecurityEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AKDESK04 on 3/28/2017.
 */
public class AppFilter extends OncePerRequestFilter {

    /**
     * Archivo de configuración
     **/

    @Value("${cibersys.token.header}")
    private String tokenHeader;

    @Value("${cibersys.services.dbm.services}")
    private String services;

    @Value("${cibersys.token.secret}")
    private String secret;

    @Value("${cibersys.token.expiration}")
    private Long expiration;


    private ApplicationSecurityEnvironment securityEnvironment = new ApplicationSecurityEnvironment();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        AntPathMatcher urlMatch = new AntPathMatcher();
        response.addHeader("Access-Control-Allow-Headers", tokenHeader);
        response.addHeader("Access-Control-Expose-Headers", tokenHeader);
        if (urlMatch.match(services + "**",
                request.getRequestURI().substring(request.getContextPath().length()))) {
            securityEnvironment.proceedInternalServiceAccessVerification(tokenHeader, filterChain, request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
