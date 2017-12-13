package com.cibesys.firewall.mailer.MailerUtilities;

import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
@Service
public class MailerBuilderImpl implements MailerBuilder {


    @Override
    public MimeMessagePreparator prepareMail(String subject,String resultFinal, String email,Boolean show) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(ConfigurationProperties.EMAIL_BACKEND);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);

            messageHelper.setText(resultFinal, true);


            if (show) {
                ClassPathResource cibersys_banner_success = new ClassPathResource(
                        ConfigurationProperties.CIBERSYS_BANNER_HEAD_SUCCESS);

                InputStreamSource banner_header_success = new ByteArrayResource(
                        IOUtils.toByteArray(cibersys_banner_success.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_BANNER_HEAD_SUCCESS, banner_header_success,
                        "image/png");


                ClassPathResource cibersys_check_icon_success = new ClassPathResource(
                        ConfigurationProperties.CIBERSYS_CHECK_ICON_SUCCESS);

                InputStreamSource check_icon_success = new ByteArrayResource(
                        IOUtils.toByteArray(cibersys_check_icon_success.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_CHECK_ICON_SUCCESS, check_icon_success,
                        "image/png");
            }else{
                ClassPathResource header = new ClassPathResource(ConfigurationProperties.CIBERSYS_HEADER_EMAIL);

                InputStreamSource head = new ByteArrayResource(IOUtils.toByteArray(header.getInputStream()));

                messageHelper.addInline(ConfigurationProperties.CIBERSYS_HEADER_EMAIL, head,
                        "image/png");
            }

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

        return messagePreparator;
    }
}
