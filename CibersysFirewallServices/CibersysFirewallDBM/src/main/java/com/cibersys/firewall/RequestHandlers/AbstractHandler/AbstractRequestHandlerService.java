package com.cibersys.firewall.RequestHandlers.AbstractHandler;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/16/2017.
 */
public interface AbstractRequestHandlerService<T> {
    T proceedRequest(Map<String, String> body);
}
