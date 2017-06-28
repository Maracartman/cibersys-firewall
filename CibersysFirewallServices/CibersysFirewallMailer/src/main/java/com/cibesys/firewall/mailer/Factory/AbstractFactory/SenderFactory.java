package com.cibesys.firewall.mailer.Factory.AbstractFactory;

import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;

/**
 * Created by AKDESK25 on 6/21/2017.
 */
public interface SenderFactory<T extends AbstractEmailSenderService,E>{
    T getSender();
    T getSender(E params);
}
