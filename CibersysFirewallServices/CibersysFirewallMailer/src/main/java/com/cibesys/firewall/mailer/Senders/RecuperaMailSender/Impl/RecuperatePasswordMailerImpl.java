package com.cibesys.firewall.mailer.Senders.RecuperaMailSender.Impl;


import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import com.cibesys.firewall.mailer.Senders.RecuperaMailSender.RecuperateMailSenderService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


/**
 * se encaga de enviar el correo de validacion de claves
 *
 * @author luigi.maracara@gmail.com,
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RecuperatePasswordMailerImpl extends AbstractEmailSenderService implements RecuperateMailSenderService {
    public Boolean sendEmail() throws Exception {
        ClassPathResource resourceEmail = new ClassPathResource(
                ConfigurationProperties.PATH_EMAIL_RECUPERATE_PASSWORD);

        if (resourceEmail.getFile().exists() && resourceEmail.getFile().canRead()) {

            String result = new BufferedReader(
                    new InputStreamReader(resourceEmail.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

            result = result.replace("{{validation_code}}", this.token);
            result = result.replace("{{nombre}}", this.name);
            result = result.replace("{{apellido}}", this.surName);
            result = result.replace("{{username}}", this.email);
            final String resultFinal = result;
            MimeMessagePreparator messagePreparator = mailerBuilder.prepareMail(
                    ConfigurationProperties.SUBJECT_EMAIL_RECUPERATE_PASSWORD,resultFinal,this.email,false);
            return sendMail(messagePreparator);


        } else {
            throw new Exception("No se pudo conseguir el archivo de correo");
        }
    }
}
