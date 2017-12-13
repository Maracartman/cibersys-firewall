package com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeService.impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.PasswordChangeService.PasswordChange;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.PasswordChangeRequest;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.PasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Maracartman on 18/7/2017.
 */

@Service
public class PasswordChangeImpl extends AbstractRequestHandler<ResponseEntity<?>> implements PasswordChange {
    private TokenUtils tokenUtils ;

    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body,Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        PasswordChangeRequestDTO passwordChangeRequestDTO = objectMapper.convertValue(body,PasswordChangeRequestDTO.class);
        UserDTO userToken = tokenUtils.getUserFromToken(header.get("x-auth-token"));
        ArrayList<PasswordChangeRequestDTO> array = new ArrayList<PasswordChangeRequestDTO>();
        array.add(passwordChangeRequestDTO);

        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest(
                array);

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
