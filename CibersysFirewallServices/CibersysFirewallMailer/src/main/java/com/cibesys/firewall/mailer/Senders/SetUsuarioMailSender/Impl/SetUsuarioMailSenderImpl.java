package com.cibesys.firewall.mailer.Senders.SetUsuarioMailSender.Impl;

import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import com.cibesys.firewall.mailer.Senders.SetUsuarioMailSender.SetUsuarioMailSender;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SetUsuarioMailSenderImpl extends AbstractEmailSenderService implements SetUsuarioMailSender {
    @Override
    public Boolean sendEmail() throws Exception {
        ClassPathResource resourceEmail = new ClassPathResource(
                ConfigurationProperties.PATH_EMAIL_WELCOME);

        if (resourceEmail.getFile().exists() && resourceEmail.getFile().canRead()) {

            String result = new BufferedReader(
                    new InputStreamReader(resourceEmail.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

            result = result.replace("{{nombre}}", this.name);
            result = result.replace("{{apellido}}", this.surName);
            result = result.replace("{{email}}", this.email);
            result = result.replace("{{password}}", this.password);
            result = result.replace("{{validation_code}}", this.password);

            final String resultFinal = result;
            MimeMessagePreparator messagePreparator = mailerBuilder.prepareMail(ConfigurationProperties.SUBJECT_EMAIL_WELCOME,resultFinal, this.email,false);

            return sendMail(messagePreparator);


        } else {
            throw new Exception("No se pudo conseguir el archivo de correo");
        }
    }
}
