package com.cibersys.firewall.RequestHandlers.LoginRequestService.impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.LoginRequestService.LoginService;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.LoginResponse;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by AKDESK04 on 3/23/2017.
 */
@Service
public class LoginServiceImpl extends AbstractRequestHandler<LoginResponse> implements LoginService {

    @Value("${cibersys.services.dbm.usuario}")
    protected String dbmUsuario;

    @Value("${cibersys.services.dbm.route}")
    protected String dbmRoute;

    @Autowired
    protected RestTemplate restTemplate;

    @Override
    public LoginResponse proceedRequest(Map<String,String> body,Map<String,String> header) {
        UserDTO user = objectMapper.convertValue(body,UserDTO.class);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> {
            headers.add(s,s2);
        });
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<UserDTO> request = new HttpEntity<UserDTO>(user,headers);
        try{
            return restTemplate.postForObject(dbmRoute + dbmUsuario,request,LoginResponse.class);
        }catch (ResourceAccessException e){
            return null;
        }
    }
}
