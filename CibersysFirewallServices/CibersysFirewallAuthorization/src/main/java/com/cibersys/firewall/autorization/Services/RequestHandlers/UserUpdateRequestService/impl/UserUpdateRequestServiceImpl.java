package com.cibersys.firewall.autorization.Services.RequestHandlers.UserUpdateRequestService.impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.autorization.Services.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/19/2017.
 */
@Service
public class UserUpdateRequestServiceImpl extends AbstractRequestHandler<ResponseEntity<?>> implements UserUpdateRequestService {

    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        UserUpdateRequestDTO updateInformation = objectMapper.convertValue(body,UserUpdateRequestDTO.class);
        HttpEntity<UserUpdateRequestDTO> request = userGeneralRequestBuilder.buildUserUpdateDTORequest(updateInformation);
        try {
            UpdateResponse consult = restTemplate.postForObject(mannagerRoute + mannagerUpdate, request, UpdateResponse.class);
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