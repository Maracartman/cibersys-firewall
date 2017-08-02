package com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
public interface AbstractPrivateRequestHandlerService<T, E> {
    T proceedRequest(E body);
}
