package com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.impl;

import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.UserUpdateRequestService.UserUpdateRequestService;
import com.cibersys.firewall.Utilities.UserGeneralRequestBuilder;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserUpdateRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPasswordChangeRequestResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/19/2017.
 */
@Service
public class UserUpdateRequestServiceImpl extends AbstractRequestHandler<AbstractResponseBody> implements UserUpdateRequestService {

    @Autowired
    private UserGeneralRequestBuilder userGeneralRequestBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header) {
        UserUpdateRequestDTO updateInformation = objectMapper.convertValue(body, UserUpdateRequestDTO.class);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> headers.add(s, s2));
        HttpEntity<UserUpdateRequestDTO> request = new HttpEntity<UserUpdateRequestDTO>(updateInformation, headers);
        UpdateResponse updateResponse = restTemplate.postForObject(dbmRoute + dbmUpdate, request, UpdateResponse.class);

        if (!updateResponse.getError()){
            if (!restTemplate.postForObject(mailerRoute + mailerUpdate, request, NewPasswordChangeRequestResponse.class)
            .getError()) {
                return new UpdateResponse(Long.valueOf(200), "Se ha cambiado la " +
                        "contraseña correctamente.", false,null);
            }
        } else {
            return new UpdateResponse(Long.valueOf(200),updateResponse.getMessage(), true, null);
        }
        return restTemplate.postForObject(dbmRoute + dbmUpdate, request, UpdateResponse.class);
    }
}
