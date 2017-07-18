package com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChange.impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChange.PasswordChange;
import com.cibersys.firewall.domain.models.DTO.model.PasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 18/7/2017.
 */

@Service
public class PasswordChangeImpl extends AbstractRequestHandler<ResponseEntity<?>> implements PasswordChange {
    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {

        PasswordChangeRequestDTO passwordChangeRequestDTO = objectMapper.convertValue(body,PasswordChangeRequestDTO.class);
        HttpEntity<PasswordChangeRequestDTO> request = userGeneralRequestBuilder.buildPasswordChangeRequestDTO(passwordChangeRequestDTO);
        try {
            UpdateResponse consult = restTemplate.postForObject(mannagerRoute + mannagerPasswordChange, request, UpdateResponse.class);
            if (!consult.getError()) {
                return ResponseEntity.ok(consult);
            } else {
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null));
            }
        } catch (ResourceAccessException e) {
            return ResponseEntity.ok(new ResponseError(Long.valueOf(401), "El servicio solicitado no se encuentra disponible",true,null));
        }

    }
}
