package com.cibersys.firewall.RequestHandlers.UserVerification.UserVerificationImpl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
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
 * Created by Luis Maracara on 6/14/2017.
 */
@Service
public class UserVerificationServiceImpl extends AbstractRequestHandler<AbstractResponseBody> implements UserVerification{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncrypter encrypter;

    @Override
    public AbstractResponseBody proceedRequest(Map<String,String> body,Map<String, String> header) {
        UserDTO user = objectMapper.convertValue(body,UserDTO.class);
        Usuario usuario = usuarioRepository.findOneByEmailAndContraseñaAndEstatus(user.getUserName(),
                user.getPassword().length()>20 ? user.getPassword() :
                        encrypter.cryptWithMD5(user.getPassword()),"1");

        return usuario != null && !usuario.getRol().equals("5")? (AbstractResponseBody) new LoginResponse(Long.valueOf(200),
                "",false,new UserDTO(usuario.getEmail(),usuario.getContraseña(),
                Integer.parseInt(usuario.getRol()))) : new ResponseError(Long.valueOf(401),"Usuario no encontrado, o no válido.",true,null);
    }

}
