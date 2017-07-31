package com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractPrivateRequestHandlerService;
import com.cibersys.firewall.autorization.Services.Utilities.UserGeneralRequestBuilder;
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

    @Value("${cibersys.services.dbm.usuario}")
    protected String dbmUsuario;

    @Value("${cibersys.services.dbm.route}")
    protected String dbmRoute;

    @Value("${cibersys.services.mannager.route}")
    protected String mannagerRoute;

    @Value("${cibersys.services.mannager.usuario}")
    protected String mannagerUsuario;

    @Value("${cibersys.services.mannager.update}")
    protected String mannagerUpdate;

    @Value("${cibersys.services.mannager.setUsuario}")
    protected String mannagerSetUsuario;

    @Value("${cibersys.services.mannager.recuperatepassword}")
    protected String mannagerRecuperatePassword;

    @Value("${cibersys.services.mannager.passwordchange}")
    protected String mannagerPasswordChange;

    @Value("${cibersys.services.mannager.setCliente}")
    protected String mannagerSetCliente;
    @Autowired
    protected UserGeneralRequestBuilder userGeneralRequestBuilder;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    protected TokenUtils tokenUtils;




    public abstract  T proceedRequest(E body, Map<String, String> header);
}
