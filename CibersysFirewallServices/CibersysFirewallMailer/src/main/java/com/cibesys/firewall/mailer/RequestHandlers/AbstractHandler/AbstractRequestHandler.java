package com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler;


import com.cibesys.firewall.mailer.Factory.MailSenderFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Luis Maracara on 6/16/2017.
 */
public abstract class AbstractRequestHandler<T> {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MailSenderFactory mailSenderFactory;

    public abstract T proceedRequest(Map<String, String> body);

}
