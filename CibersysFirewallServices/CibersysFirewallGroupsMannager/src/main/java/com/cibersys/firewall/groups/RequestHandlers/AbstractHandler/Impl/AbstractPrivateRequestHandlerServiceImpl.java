package com.cibersys.firewall.groups.RequestHandlers.AbstractHandler.Impl;




import com.cibersys.firewall.groups.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
public abstract class AbstractPrivateRequestHandlerServiceImpl<T,E>
        implements AbstractPrivateRequestHandlerService<T,E> {

    @Value("${cibersys.token.header}")
    protected String tokenHeader;

    @Value("${cibersys.token.secret}")
    protected String secret;

    @Value("${cibersys.token.expiration}")
    protected Long expiration;

    @Value("${cibersys.services.dbm.route}")
    protected String dbmRoute;

    @Value("${cibersys.services.dbm.group}")
    protected String dbmGroup;

    @Value("${cibersys.services.mailer.route}")
    protected String mailerRoute;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TokenUtils tokenUtils;

    public abstract  T proceedRequest(E body, Map<String, String> header);
}
