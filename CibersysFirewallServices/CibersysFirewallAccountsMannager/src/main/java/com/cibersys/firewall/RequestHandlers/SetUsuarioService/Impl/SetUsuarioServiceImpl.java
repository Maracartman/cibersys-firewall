package com.cibersys.firewall.RequestHandlers.SetUsuarioService.Impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Service
public class SetUsuarioServiceImpl extends AbstractRequestHandler<AbstractResponseBody>
        implements SetUsuarioService {
    @Override
    public AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        SetUsuarioRequestDTO setUsuarioRequest
                = objectMapper.convertValue(body, SetUsuarioRequestDTO.class);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> headers.add(s, s2));
        HttpEntity<SetUsuarioRequestDTO> request = new HttpEntity<SetUsuarioRequestDTO>(setUsuarioRequest, headers);
        try {
            SetUsuarioReponseDTO consult = restTemplate.postForObject(dbmRoute + dbmSetUsuario, request, SetUsuarioReponseDTO.class);
            if (!consult.getError()){

                /**
                 * Aqui se retorna la información obtenida del servicio de correo
                 *
                 * **/
                return consult;
            } else {
                return new ResponseError(Long.valueOf(401), consult.getMessage(),
                        true, null);
            }
        } catch (ResourceAccessException e) {
            return new ResponseError(Long.valueOf(401), "El servicio solicitado no se encuentra disponible", true, null);
        }
    }
}
