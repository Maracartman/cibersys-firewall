package com.cibersys.firewall.groups.RequestHandlers.GroupRequestService.Impl;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.GroupResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.groups.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.groups.RequestHandlers.GroupRequestService.GroupRequestService;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Service
public class GroupRequestServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody,
        GroupRequestDTO> implements GroupRequestService {
    @Override
    public AbstractResponseBody proceedRequest(GroupRequestDTO body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        header.forEach((s, s2) -> headers.add(s, s2));
        HttpEntity<GroupRequestDTO> request = new HttpEntity<GroupRequestDTO>(body, headers);
        try {
            GroupResponseDTO consult = restTemplate.postForObject(dbmRoute + dbmGroup,
                    request, GroupResponseDTO.class);

            return !consult.getError()?consult: new ResponseError(Long.valueOf(401), consult.getMessage(),
                    true, null);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
