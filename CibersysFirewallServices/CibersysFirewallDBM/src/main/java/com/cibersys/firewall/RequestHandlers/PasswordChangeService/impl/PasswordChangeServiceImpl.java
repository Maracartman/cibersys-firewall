package com.cibersys.firewall.RequestHandlers.PasswordChangeService.impl;

import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.PasswordChangeService.PasswordChangeService;
import com.cibersys.firewall.Utilities.TimingUtilities;
import com.cibersys.firewall.converter.PasswordEncrypter;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.PasswordChangeRequestDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UserUpdateResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.UpdateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Maracartman on 18/7/2017.
 */
@Service
public class PasswordChangeServiceImpl extends AbstractRequestHandler<UpdateResponse> implements PasswordChangeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncrypter encrypter;

    @Autowired
    private TimingUtilities timingUtilities;

    @Override
    public UpdateResponse proceedRequest(Map<String, String> body,Map<String, String> header) {
        PasswordChangeRequestDTO u = objectMapper.convertValue(body,PasswordChangeRequestDTO.class);

        Usuario usuario = usuarioRepository.findOneByEmailAndContraseñaAndEstatus(u.getEmail(),
                encrypter.cryptWithMD5(u.getOldPassword()),"1");
        if(usuario != null){
            usuario.setContraseña(encrypter.cryptWithMD5(u.getNewPassword()));
                usuarioRepository.save(usuario);
                return new UpdateResponse(Long.valueOf(200),"El usuario ha sido actualizado.",false,new UserUpdateResponseDTO(
                        u.getEmail(),u.getNewPassword()));
        }
        else{
            return new UpdateResponse(Long.valueOf(401),"código de validación o correo no válidos",
                    true,null);
        }
    }
}
