package com.cibersys.firewall.RequestHandlers.SetUsuarioService.Impl;


import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioResponse;
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
            if (!consult.getError() && (setUsuarioRequest.getAction().equalsIgnoreCase("0") ||(setUsuarioRequest.getAction().equalsIgnoreCase("1") &&
            consult.getResponse() != null && consult.getResponse().get(0).getEdited_mail())) ) {
                /**
                 * Aqui se retorna la información obtenida del servicio de correo
                 *
                 * **/
                SetUsuarioResponse response_body = consult.getResponse().get(0);
                UserDTO created_user = new UserDTO();
                created_user.setUserName(response_body.getEmail());
                created_user.setPassword(response_body.getPassword());
                created_user.setName(response_body.getName());
                created_user.setLastName(response_body.getLastName());

                HttpEntity<UserDTO> request2 = new HttpEntity<>(created_user, headers);
                SetUsuarioReponseDTO mailResult = restTemplate.postForObject(
                        mailerRoute + mailerSetUsuario, request2, SetUsuarioReponseDTO.class);
                return mailResult.getError() == false ? consult : mailResult;
            }
            return  consult;
        } catch (ResourceAccessException e) {
            return new ResponseError(Long.valueOf(401), "El servicio solicitado no se encuentra disponible", true, null);
        }
    }
}
