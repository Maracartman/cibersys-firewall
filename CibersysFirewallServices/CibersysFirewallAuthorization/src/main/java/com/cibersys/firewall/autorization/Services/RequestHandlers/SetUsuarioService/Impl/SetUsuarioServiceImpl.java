package com.cibersys.firewall.autorization.Services.RequestHandlers.SetUsuarioService.Impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.autorization.Services.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Service
public class SetUsuarioServiceImpl extends AbstractRequestHandler<ResponseEntity<?>>
        implements SetUsuarioService {
    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        SetUsuarioRequestDTO setUsuarioRequest
                = objectMapper.convertValue(body, SetUsuarioRequestDTO.class);
        HttpEntity<SetUsuarioRequestDTO> request =
                userGeneralRequestBuilder.buildSetUsuarioRequestDTO(setUsuarioRequest,header);
        try{
            SetUsuarioReponseDTO consult = restTemplate.postForObject(mannagerRoute + mannagerSetUsuario, request, SetUsuarioReponseDTO.class);
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
