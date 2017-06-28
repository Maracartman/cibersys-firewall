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
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


/**
 * se encaga de enviar el correo de validacion de claves
 *
 * @author leonelsoriano3@gmail.co,
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
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom(ConfigurationProperties.EMAIL_BACKEND);
                messageHelper.setTo(this.email);
                messageHelper.setSubject(ConfigurationProperties.SUBJECT_EMAIL_RECUPERATE_PASSWORD);

                messageHelper.setText(resultFinal, true);


                ClassPathResource header = new ClassPathResource(ConfigurationProperties.CIBERSYS_HEADER_EMAIL);

                InputStreamSource head = new ByteArrayResource(IOUtils.toByteArray(header.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_HEADER_EMAIL, head,
                        "image/png");

                ClassPathResource logoEmail = new ClassPathResource(ConfigurationProperties.CIBERSYS_LOGO_EMAIL);

                InputStreamSource imageSource = new ByteArrayResource(IOUtils.toByteArray(logoEmail.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_EMAIL, imageSource,
                        "image/png");


                ClassPathResource logoLinkedin = new ClassPathResource(ConfigurationProperties.CIBERSYS_LOGO_LINKEDIN);

                InputStreamSource imageSourceLinkedin = new ByteArrayResource(
                        IOUtils.toByteArray(logoLinkedin.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_LINKEDIN, imageSourceLinkedin,
                        "image/png");


                ClassPathResource logoFacebook = new ClassPathResource(ConfigurationProperties.CIBERSYS_LOGO_FACEBOOK);

                InputStreamSource imageSourceFacebook = new ByteArrayResource(
                        IOUtils.toByteArray(logoFacebook.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_FACEBOOK, imageSourceFacebook,
                        "image/png");


                ClassPathResource logoProfileTwitter = new ClassPathResource(
                        ConfigurationProperties.CIBERSYS_LOGO_TWITTER);

                InputStreamSource imageSourceProfileTwitter = new ByteArrayResource(
                        IOUtils.toByteArray(logoProfileTwitter.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_TWITTER, imageSourceProfileTwitter,
                        "image/png");

                ClassPathResource logoGoogle = new ClassPathResource(ConfigurationProperties.CIBERSYS_LOGO_GOOGLE);

                InputStreamSource imageSourceGoogle = new ByteArrayResource(
                        IOUtils.toByteArray(logoGoogle.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_GOOGLE, imageSourceGoogle,
                        "image/png");

            };

            return sendMail(messagePreparator);


        } else {
            throw new Exception("No se pudo conseguir el archivo de correo");
        }
    }
}
