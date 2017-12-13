package com.cibersys.firewall.groups.RequestHandlers.AbstractHandler;

import java.util.Map;

/**
 * Created by Maracartman on 31/7/2017.
 */
public interface AbstractPrivateRequestHandlerService<T,E> {

    T proceedRequest(E body, Map<String, String> header);
}
