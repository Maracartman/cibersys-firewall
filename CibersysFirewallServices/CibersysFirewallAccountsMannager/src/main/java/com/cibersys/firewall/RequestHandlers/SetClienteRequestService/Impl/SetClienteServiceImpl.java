package com.cibersys.firewall.RequestHandlers.SetClienteRequestService.Impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.RequestHandlers.SetClienteRequestService.SetClienteService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPanelClientResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Service
public class SetClienteServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody,NewPanelClientRequestDTO>
implements SetClienteService {
    @Override
    public AbstractResponseBody proceedRequest(NewPanelClientRequestDTO body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> headers.add(s, s2));
        HttpEntity<NewPanelClientRequestDTO> request = new HttpEntity<NewPanelClientRequestDTO>(body, headers);
        try{
            NewPanelClientResponseDTO consult = restTemplate.postForObject(dbmRoute + dbmSetCliente,
                    request, NewPanelClientResponseDTO.class);
            if (!consult.getError()) {
                return consult;
            } else {
                return new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null);
            }
        }catch (ResourceAccessException e){

        }



        return null;
    }
}
