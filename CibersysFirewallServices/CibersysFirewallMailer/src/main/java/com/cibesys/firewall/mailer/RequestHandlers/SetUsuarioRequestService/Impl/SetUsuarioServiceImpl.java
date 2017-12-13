package com.cibesys.firewall.mailer.RequestHandlers.SetUsuarioRequestService.Impl;

import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibesys.firewall.mailer.Factory.Parameters.MailSenderParameters;
import com.cibesys.firewall.mailer.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandlerImpl;
import com.cibesys.firewall.mailer.RequestHandlers.SetUsuarioRequestService.SetUsuarioService;
import com.cibesys.firewall.mailer.Senders.Abstract.AbstractEmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by AKDESK25 on 8/2/2017.
 */
@Service
public class SetUsuarioServiceImpl extends AbstractRequestHandlerImpl<ResponseEntity<?>> implements SetUsuarioService {
    @Override
    public ResponseEntity<?> proceedRequest(Map<String, String> body) {
        UserDTO request = objectMapper.convertValue(body,UserDTO.class);

        AbstractEmailSenderService sender = mailSenderFactory.constructSender(
                new MailSenderParameters("setUsuario",request.getName(),request.getUserName(),request.getLastName(),
                        request.getPassword()));
        try {
            sender.sendEmail();
            return ResponseEntity.ok(new SetUsuarioReponseDTO(Long.valueOf(200),
                    "Correo enviado correctamente",false,null));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.ok(new SetUsuarioReponseDTO(Long.valueOf(200),
                    "Los datos no son válidos.",true,null));
        }
    }
}
