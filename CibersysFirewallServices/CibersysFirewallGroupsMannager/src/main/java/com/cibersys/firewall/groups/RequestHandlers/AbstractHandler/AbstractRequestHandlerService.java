package com.cibersys.firewall.groups.RequestHandlers.AbstractHandler;



import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
public interface AbstractRequestHandlerService<T> {
    AbstractResponseBody proceedRequest(Map<String, String> body, Map<String, String> header);
}
