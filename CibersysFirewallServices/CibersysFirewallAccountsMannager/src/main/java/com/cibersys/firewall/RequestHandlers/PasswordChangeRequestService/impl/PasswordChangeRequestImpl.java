package com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.PasswordChangeRequestService.PasswordChangeRequest;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.NewPasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/21/2017.
 */
@Service
public class PasswordChangeRequestImpl extends AbstractRequestHandler<AbstractResponseBody> implements PasswordChangeRequest {


    @Override
    public AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header) {
        NewPasswordChangeRequestDTO newPasswordRequest = objectMapper.convertValue(body, NewPasswordChangeRequestDTO.class);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> {
            headers.add(s, s2);
        });
        HttpEntity<NewPasswordChangeRequestDTO> request = new HttpEntity<NewPasswordChangeRequestDTO>(newPasswordRequest, headers);
        try {
            NewPasswordChangeRequestResponse consult = restTemplate.postForObject(dbmRoute + dbmRecuperatePassword, request, NewPasswordChangeRequestResponse.class);
            if (!consult.getError()) {
                /**El DBM pudo satisfactoriamente crear un nuevo codigo de verificación antes de proceder
                 * llamamos al micro servicio de @MAILER para que envie el correo electrónico**/
                request = new HttpEntity<>(consult.getResponse(), headers);
                NewPasswordChangeRequestResponse mailResult = restTemplate.postForObject(
                        mailerRoute + mailerPasswordChange, request, NewPasswordChangeRequestResponse.class);
                return mailResult;
            } else {
                return new ResponseError(Long.valueOf(401), consult.getMessage(),
                        true, null);
            }
        } catch (ResourceAccessException e) {
            return new ResponseError(Long.valueOf(401), "El servicio de base de datos no se encuentra disponible", true, null);
        }
    }

//
}
