package com.cibesys.firewall.mailer.Factory;


import com.cibesys.firewall.mailer.Factory.AbstractFactory.AbstractSenderFactory;
import com.cibesys.firewall.mailer.Factory.AbstractFactory.SenderFactory;
import com.cibesys.firewall.mailer.Factory.Parameters.MailSenderParameters;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/22/2017.
 */
@Service
public class MailSenderFactory extends AbstractSenderFactory<AbstractEmailSenderService,MailSenderParameters>
implements SenderFactory<AbstractEmailSenderService,MailSenderParameters>{
    @Autowired
    @Qualifier("requestMailerCollection")
    private Map<String, Class<?>> requestMailerCollection;

    @Autowired
    private ApplicationContext context;



    @Override
    public AbstractEmailSenderService constructSender(MailSenderParameters params) {
        AbstractEmailSenderService mailer =
                (AbstractEmailSenderService) context.getBean(requestMailerCollection.get(params.getRequest()));

        mailer.setEmail(params.getEmail());
        mailer.setPassword(params.getPassword());
        mailer.setJob(params.getJob());
        mailer.setName(params.getName());
        mailer.setSurName(params.getSurName());
        mailer.setToken(params.getToken());
        mailer.setType(params.getType());
        mailer.setUsername(params.getUsername());

        return mailer;
    }
}
