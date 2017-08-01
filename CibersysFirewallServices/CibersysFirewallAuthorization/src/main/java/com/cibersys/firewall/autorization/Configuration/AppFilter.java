package com.cibersys.firewall.autorization.Configuration;


import com.cibersys.firewall.autorization.Services.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.LoginResponse;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
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

    @Value("${cibersys.services.mannager.route}")
    protected String mannagerRoute;
    @Value("${cibersys.services.mannager.usuario}")
    protected String mannagerUsuario;
    @Value("${cibersys.services.mannager.update}")
    protected String mannagerUpdate;
    @Value("${cibersys.token.header}")
    private String tokenHeader;
    @Value("${cibersys.services.services.public}")
    private String public_service;
    @Value("${cibersys.services.services.private}")
    private String private_services;
    
    @Value("${cibersys.token.secret}")
    private String secret;
    @Value("${cibersys.token.expiration}")
    private Long expiration;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserGeneralRequestBuilder userGeneralRequestBuilder;

    private TokenUtils tokenUtils = new TokenUtils(secret, expiration);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        AntPathMatcher urlMatch = new AntPathMatcher();
        response.addHeader("Access-Control-Allow-Headers", tokenHeader);
        response.addHeader("Access-Control-Expose-Headers", tokenHeader);
        if (urlMatch.match(public_service + "**",
                request.getRequestURI().substring(request.getContextPath().length()))) {
            filterChain.doFilter(request, response);
        } else if (urlMatch.match(private_services + "**",
                request.getRequestURI().substring(request.getContextPath().length()))) {
            if (request.getHeader(tokenHeader) == null) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
            } else {
                tokenUtils = new TokenUtils(secret, expiration);

                UserDTO userToken = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
                if (userToken == null) {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es valido.");
                } else {
                    String requested_service = request.getRequestURL().toString().split("/")
                            [request.getRequestURL().toString().split("/").length -1];
                    if(verifyValidRequest(userToken,requested_service)){
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                        authorities.add(new SimpleGrantedAuthority("ADMIN"));
                        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userToken.getUserName(), userToken.getPassword(), authorities);
                        SecurityContextHolder.getContext().setAuthentication(authRequest);
                        filterChain.doFilter(request, response);
                    }else{
                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "El usuario no posee los permisos para " +
                                "este servicio.");
                    }
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
    private Boolean verifyValidRequest(UserDTO userToken, String requested_service) {
        HttpEntity<UserDTO> request1 = userGeneralRequestBuilder.buildUserDTORequest(userToken);
        UserDTO consult = restTemplate.postForObject(mannagerRoute + mannagerUsuario, request1, LoginResponse.class).getResponse();
        return consult != null && (userToken.getUserName().equals(consult.getUserName())) && verifyRolRequeriment(userToken,requested_service);
    }

    private boolean verifyRolRequeriment(UserDTO userToken, String requested_service) {
        switch (requested_service){
            case "setCliente":
                return userToken.getIdRol().toString().equalsIgnoreCase("1")? true : false;
            default:
                return true;
        }
    }
}
