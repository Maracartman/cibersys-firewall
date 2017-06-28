package com.cibesys.firewall.mailer.Factory.AbstractFactory;

import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;

/**
 * Created by AKDESK25 on 6/21/2017.
 */
public abstract class AbstractSenderFactory<T extends AbstractEmailSenderService,E> implements SenderFactory<T,E> {
    @Override
    public T getSender() {
        return null;
    }

    @Override
    public T getSender(E params) {
        return this.constructSender(params);
    }
    protected abstract T constructSender(E params);
}
