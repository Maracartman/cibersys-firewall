package com.cibersys.firewall.RequestHandlers.PasswordChange.impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.PasswordChange.PasswordChange;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.PasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 18/7/2017.
 */

@Service
public class PasswordChangeImpl extends AbstractRequestHandler<AbstractResponseBody> implements PasswordChange {
    @Override
    public AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header) {
        PasswordChangeRequestDTO passwordChangeRequestDTO = objectMapper.convertValue(body,PasswordChangeRequestDTO.class);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> headers.add(s, s2));
        HttpEntity<PasswordChangeRequestDTO> request =new HttpEntity<PasswordChangeRequestDTO>(passwordChangeRequestDTO, headers);
        try {
            UpdateResponse consult = restTemplate.postForObject(dbmRoute + dbmPasswordChange, request, UpdateResponse.class);
            if (!consult.getError()) {
                return consult;
            } else {
                return new UpdateResponse(Long.valueOf(200),"Se ha producido un error", true, null);
            }
        } catch (ResourceAccessException e) {
            return new UpdateResponse(Long.valueOf(200),"Se ha producido un error", true, null);
        }

    }
}
