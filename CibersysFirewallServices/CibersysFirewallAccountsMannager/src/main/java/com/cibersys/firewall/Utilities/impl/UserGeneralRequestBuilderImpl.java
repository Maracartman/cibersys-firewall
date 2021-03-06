package com.cibersys.firewall.Utilities.impl;


import com.cibersys.firewall.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.UserUpdateRequestDTO;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by Luis Maracara on 6/19/2017.
 */
@Service
public class UserGeneralRequestBuilderImpl implements UserGeneralRequestBuilder {

    @Value("${cibersys.token.secret}")
    protected String secret;
    @Value("${cibersys.token.expiration}")
    protected Long expiration;
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private TokenUtils tokenUtils;
    private  MultiValueMap<String, String> headers;

    @PostConstruct
    public void init(){
        tokenUtils = new TokenUtils(secret, expiration);
        headers = new LinkedMultiValueMap<String, String>();
        try {
            headers.add("X-Auth-Token",
                    tokenUtils.generateToken(objectMapper.writeValueAsString(
                            new UserDTO("internal@cibersys", "internal@cibersys"))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }
    @Override
    public HttpEntity<UserDTO> buildUserDTORequest(UserDTO body) {
//        tokenUtils = new TokenUtils(secret, expiration);
        return new HttpEntity<UserDTO>(body, headers);
    }
    @Override
    public HttpEntity<UserUpdateRequestDTO> buildUserUpdateDTORequest(UserUpdateRequestDTO u) {
        return new HttpEntity<UserUpdateRequestDTO>(u,headers);
    }
}
