package com.cibersys.firewall.autorization.Services.RequestHandlers.LoginRequestService.impl;


import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.LoginRequestService.LoginService;
import com.cibersys.firewall.autorization.Services.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.LoginResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by AKDESK04 on 3/23/2017.
 */
@Service
public class LoginServiceImpl extends AbstractRequestHandler<ResponseEntity<?>> implements LoginService {





    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        UserDTO user = objectMapper.convertValue(body,UserDTO.class);
        tokenUtils = new TokenUtils(secret,expiration);
        HttpEntity<UserDTO> request = userGeneralRequestBuilder.buildUserDTORequest(user);
        try {
            UserDTO consult = restTemplate.postForObject(mannagerRoute + mannagerUsuario, request, LoginResponse.class).getResponse();
            if (consult != null) {
                HttpHeaders responseHeader = new HttpHeaders();
                responseHeader.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                try {
                    responseHeader.add("X-Auth-Token", tokenUtils.generateToken(objectMapper.writeValueAsString(user)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                consult.setPassword(user.getPassword());
                return ResponseEntity.ok().headers(responseHeader).body(new LoginResponse(Long.valueOf(200), "", false, consult));
            } else {
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401), "El usuario no se ha encontrado",
                       true,null));
            }
        } catch (ResourceAccessException e) {
            return ResponseEntity.ok(new ResponseError(Long.valueOf(401), "El servicio no puede ser localizado, intente" +
                    "nuevamente más tarde",true,null));
        }
    }
}
