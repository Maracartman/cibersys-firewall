package com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler.Impl;

import com.cibesys.firewall.mailer.Factory.MailSenderFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
public abstract class AbstractPrivateRequestHandlerImpl<T,E> {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MailSenderFactory mailSenderFactory;

    public abstract T proceedRequest(E body);
}
