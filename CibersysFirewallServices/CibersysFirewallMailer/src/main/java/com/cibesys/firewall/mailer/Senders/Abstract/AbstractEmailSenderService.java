package com.cibesys.firewall.mailer.Senders.Abstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * fachada para el envio de correos
 *
 * @author leonelsoriano3@gmail.com
 */
@Component
public abstract class AbstractEmailSenderService{

    @Autowired
    protected JavaMailSender sender;

    protected  String username;

    protected  String token;

    protected  String email;

    protected  String job;

    protected  String name;

    protected  String surName;

    protected  String type;

    protected String password;

    protected String confirmPassword;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract Boolean sendEmail() throws Exception;

    protected boolean send() {

        try {
            sendEmail();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMail(MimeMessagePreparator preparator){
        try {
            sender.send(preparator);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
