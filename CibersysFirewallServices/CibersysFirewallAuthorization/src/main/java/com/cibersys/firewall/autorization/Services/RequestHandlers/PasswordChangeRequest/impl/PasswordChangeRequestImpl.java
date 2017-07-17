package com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeRequest.impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeRequest.PasswordChangeRequest;
import com.cibersys.firewall.autorization.Services.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.model.NewPasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/21/2017.
 */
@Service
public class PasswordChangeRequestImpl extends AbstractRequestHandler<ResponseEntity<?>> implements PasswordChangeRequest {

    @Autowired
    private UserGeneralRequestBuilder userGeneralRequestBuilder;


    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        NewPasswordChangeRequestDTO newPasswordRequest = objectMapper.convertValue(body,NewPasswordChangeRequestDTO.class);
        HttpEntity<NewPasswordChangeRequestDTO> request = userGeneralRequestBuilder.buildNewPasswordChangeRequestDTO(newPasswordRequest);
        try{
            NewPasswordChangeRequestResponse consult =
                    restTemplate.postForObject(mannagerRoute + mannagerPasswordChange, request, NewPasswordChangeRequestResponse.class);
            if (!consult.getError()) {
                return ResponseEntity.ok(consult);
            } else {
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null));
            }
        }catch (ResourceAccessException e){
            return ResponseEntity.ok(new ResponseError(Long.valueOf(401), "El servicio solicitado no se encuentra disponible",true,null));
        }
    }
}
