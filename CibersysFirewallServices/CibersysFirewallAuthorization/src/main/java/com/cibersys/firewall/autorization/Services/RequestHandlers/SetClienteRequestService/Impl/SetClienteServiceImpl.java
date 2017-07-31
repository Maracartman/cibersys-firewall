package com.cibersys.firewall.autorization.Services.RequestHandlers.SetClienteRequestService.Impl;


import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.autorization.Services.RequestHandlers.SetClienteRequestService.SetClienteService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPanelClientResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
@Service
public class SetClienteServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<ResponseEntity,NewPanelClientRequestDTO>
implements SetClienteService{
    @Override
    public ResponseEntity proceedRequest(NewPanelClientRequestDTO body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        HttpEntity<NewPanelClientRequestDTO> request =
                userGeneralRequestBuilder.buildSetUsuarioRequestDTO(body,header);
        try{
            NewPanelClientResponseDTO consult = restTemplate.postForObject(mannagerRoute + mannagerSetCliente,
                    request, NewPanelClientResponseDTO.class);
            if (!consult.getError()) {
                return ResponseEntity.ok(consult);
            } else {
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null));
            }
        }catch (ResourceAccessException e){

        }



        return null;
    }
}
