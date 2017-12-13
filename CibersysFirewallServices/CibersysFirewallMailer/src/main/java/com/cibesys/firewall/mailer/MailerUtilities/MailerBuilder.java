package com.cibesys.firewall.mailer.MailerUtilities;

import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
public interface MailerBuilder {

    MimeMessagePreparator prepareMail(String subject,String resultFinal,String email,Boolean show);
}
