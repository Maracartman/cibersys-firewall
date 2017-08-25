package com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.Utilities.ManagerToken;
import com.cibersys.firewall.converter.PasswordEncrypter;
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

    @Value("${cibersys.token.secret}")
    protected String secret;

    @Value("${cibersys.token.expiration}")
    protected Long expiration;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TokenUtils tokenUtils;

    @Autowired
    protected PasswordEncrypter passwordEncrypter;

    @Autowired
    protected ManagerToken managerToken;

    public abstract  T proceedRequest(E body, Map<String, String> header);
}
