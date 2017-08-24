package com.cibersys.firewall.security;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by AKDESK25 on 8/15/2017.
 */

public class ApplicationSecurityEnvironment {

    private TokenUtils tokenUtils;
    private final String secret = "1234567890*-+/AbCdEfGhIjKlMnOpQrStUvWxYz.";
    private final Long expiration = Long.valueOf(604800);

    public void proceedRequest(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response, UserDTO userToken){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userToken.getUserName(), userToken.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void proceedInternalServiceAccessVerification(String tokenHeader,FilterChain filterChain,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response){
        try {
        if (request.getHeader(tokenHeader) == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
        } else {
            tokenUtils = new TokenUtils(secret,expiration);
            UserDTO userToken = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
            if (userToken == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
            } else {
                if (userToken.getUserName().trim().equals("internal@cibersys") && userToken.getPassword().trim().equals("internal@cibersys")){
                    proceedRequest(filterChain,request,response,userToken);
                }else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El usuario no es valido.");
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
