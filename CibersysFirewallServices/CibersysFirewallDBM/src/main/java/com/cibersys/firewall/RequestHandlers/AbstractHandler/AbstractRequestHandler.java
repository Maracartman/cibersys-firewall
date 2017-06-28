package com.cibersys.firewall.RequestHandlers.AbstractHandler;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/16/2017.
 */
public abstract class AbstractRequestHandler<T> {

    @Value("${cibersys.token.header}")
    protected String tokenHeader;

    @Value("${cibersys.token.secret}")
    protected String secret;

    @Value("${cibersys.token.expiration}")
    protected Long expiration;

    @Value("${cibersys.services.dbm.usuario}")
    protected String usuarioServiceUrl;

    @Value("${cibersys.services.dbm.route}")
    protected String currentRoute;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TokenUtils tokenUtils;


    public abstract T proceedRequest(Map<String, String> body);

}
