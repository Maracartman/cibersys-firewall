package com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler;



import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
public interface AbstractRequestHandlerService<T> {
    T proceedRequest(Map<String, String> body);
}
