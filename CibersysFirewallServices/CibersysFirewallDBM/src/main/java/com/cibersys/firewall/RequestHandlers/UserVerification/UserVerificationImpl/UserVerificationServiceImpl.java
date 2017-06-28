package com.cibersys.firewall.RequestHandlers.UserVerification.UserVerificationImpl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.converter.PasswordEncrypter;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.UserVerification.UserVerification;
import com.cibersys.firewall.domain.models.DTO.responseDTO.LoginResponse;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by AKDESK25 on 6/14/2017.
 */
@Service
public class UserVerificationServiceImpl extends AbstractRequestHandler<AbstractResponseBody> implements UserVerification{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private PasswordEncrypter encrypter = new PasswordEncrypter();

    @Override
    public AbstractResponseBody proceedRequest(Map body) {
        UserDTO user = objectMapper.convertValue(body,UserDTO.class);
        Usuario usuario = usuarioRepository.findOneByEmailAndContraseñaAndEstatus(user.getUserName(),encrypter.cryptWithMD5(user.getPassword()),"1");
        return usuario != null? (AbstractResponseBody) new LoginResponse(Long.valueOf(200),"",false,new UserDTO(usuario.getEmail(),usuario.getContraseña())) : new ResponseError(Long.valueOf(401),"Usuario no encontrado",true,null);
    }
}
