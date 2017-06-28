package com.cibesys.firewall.mailer.Senders.NewPasswordSender.Impl;


import com.cibesys.firewall.mailer.Configuration.ConfigurationProperties;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import com.cibesys.firewall.mailer.Senders.NewPasswordSender.NewPasswordSender;
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

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NewPasswordSenderImpl extends AbstractEmailSenderService implements NewPasswordSender {


	@Override
	public Boolean sendEmail() throws Exception {
		
		ClassPathResource resourceEmail = new ClassPathResource(
				ConfigurationProperties.PATH_EMAIL_SUCCESS);
		
		
		if(resourceEmail.getFile().exists() && resourceEmail.getFile().canRead() ){
			
			String result = new BufferedReader(
					new InputStreamReader(resourceEmail.getInputStream()))
					  .lines().collect(Collectors.joining("\n"));
			
		       
			final String resultFinal = result;
		    MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		        messageHelper.setFrom(ConfigurationProperties.EMAIL_BACKEND);
		        messageHelper.setTo(this.email);
		        messageHelper.setSubject(ConfigurationProperties.SUBJECT_EMAIL_NEW_PASSWORD);
		        
		        messageHelper.setText(resultFinal, true);

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



				ClassPathResource cibersys_logo_icon_success = new ClassPathResource(
						ConfigurationProperties.CIBERSYS_LOGO_ICON_SUCCESS);

				InputStreamSource logo_icon_success = new ByteArrayResource(
						IOUtils.toByteArray(cibersys_logo_icon_success.getInputStream()));

				messageHelper.addInline(ConfigurationProperties.CIBERSYS_LOGO_ICON_SUCCESS, logo_icon_success,
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



		    
			
			
		}else{
			
			logger.warn(" El template de email no existe o no es de lectura ");
//			throw new Exception(" El template de email no existe o no es de lectoura ");
			return false;
		}
	}
}
