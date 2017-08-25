package com.cibersys.firewall.autorization.Services.RequestHandlers.GroupRequestService.Impl;

import com.cibersys.firewall.autorization.Services.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.autorization.Services.RequestHandlers.GroupRequestService.GroupRequestService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.GroupResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Service
public class GroupRequestServiceImpl extends AbstractPrivateRequestHandlerServiceImpl
<ResponseEntity,GroupRequestDTO> implements GroupRequestService{
    @Override
    public ResponseEntity proceedRequest(GroupRequestDTO body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        HttpEntity<GroupRequestDTO> request =
                userGeneralRequestBuilder.buildGroupRequestDTO(body,header);
        try{
            GroupResponseDTO consult = restTemplate.postForObject(groupRoute+
                    groupService,request,GroupResponseDTO.class);
            if(!consult.getError())
                return ResponseEntity.ok(consult);
            else
                return ResponseEntity.ok(new ResponseError(Long.valueOf(401),consult.getMessage(),
                        true,null));
        }catch (ResourceAccessException e){
            e.printStackTrace();
        }
        return null;
    }
}
