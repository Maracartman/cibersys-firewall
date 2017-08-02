package com.cibesys.firewall.mailer.Senders.NewPasswordSender.Impl;


import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import com.cibesys.firewall.mailer.Senders.NewPasswordSender.NewPasswordSender;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NewPasswordSenderImpl extends AbstractEmailSenderService implements NewPasswordSender {
    @Override
    public Boolean sendEmail() throws Exception {
        ClassPathResource resourceEmail = new ClassPathResource(
                ConfigurationProperties.PATH_EMAIL_SUCCESS);

        if (resourceEmail.getFile().exists() && resourceEmail.getFile().canRead()) {
            String result = new BufferedReader(
                    new InputStreamReader(resourceEmail.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

            final String resultFinal = result;
            MimeMessagePreparator messagePreparator = mailerBuilder.prepareMail(
                    ConfigurationProperties.SUBJECT_EMAIL_NEW_PASSWORD,resultFinal, this.email,true);

            return sendMail(messagePreparator);
        } else {
            logger.warn(" El template de email no existe o no es de lectura ");
            return false;
        }
    }
}
