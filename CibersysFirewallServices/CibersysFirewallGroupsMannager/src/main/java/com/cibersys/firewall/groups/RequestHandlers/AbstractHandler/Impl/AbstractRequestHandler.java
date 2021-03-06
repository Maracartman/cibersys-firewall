package com.cibersys.firewall.groups.RequestHandlers.AbstractHandler.Impl;



import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
public abstract class AbstractRequestHandler<T> {

    @Value("${cibersys.token.header}")
    protected String tokenHeader;

    @Value("${cibersys.token.secret}")
    protected String secret;

    @Value("${cibersys.token.expiration}")
    protected Long expiration;

    @Value("${cibersys.services.dbm.route}")
    protected String dbmRoute;

    @Value("${cibersys.services.mailer.route}")
    protected String mailerRoute;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TokenUtils tokenUtils;

    public abstract T proceedRequest(Map<String, String> body, Map<String, String> header);

}
