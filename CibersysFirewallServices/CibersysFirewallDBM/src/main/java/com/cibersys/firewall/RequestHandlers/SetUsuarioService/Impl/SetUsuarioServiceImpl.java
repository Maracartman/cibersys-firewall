package com.cibersys.firewall.RequestHandlers.SetUsuarioService.Impl;


import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.Utilities.ManagerToken;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioResponse;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Service
public class SetUsuarioServiceImpl extends AbstractRequestHandler<SetUsuarioReponseDTO>
        implements SetUsuarioService {

    @Autowired
    private ManagerToken managerToken;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public SetUsuarioReponseDTO proceedRequest(Map<String, String> body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        try {
            /**
             * Obtenemos el usuario que hace la petición de registro o actualizacion de usuario.
             *
             * **/
            UserDTO requester_user = tokenUtils.getUserFromToken(header.get("requester_user"));
            /**
             * Obtenemos la información (request) a registrar
             *
             * **/
            SetUsuarioRequestDTO request = objectMapper.convertValue(body, SetUsuarioRequestDTO.class);
            /**
             * verificamos los roles dependiendo de lo solicitado y el tipo de rol procedemos a realizar el registro del usuario
             * **/
            switch (requester_user.getIdRol()) {
                case 1:
                    /**
                     * El que registra es un administrador (1)
                     * 2. El usuario otro usuario Cibersys (rol igual a 2). Este es un usuario Cibersys que se crea para que atienda las solicitudes de los clientes y es creado exclusivamente por el administrador Cibersys (rol igual a 1).
                     3. El usuario administrador Cliente (rol igual a 3). Este es la persona de contacto del cliente y se crea cuando creamos al cliente. Este usuario es creado exclusivamente por el administrador Cibersys (rol igual a 1).
                     * **/
                    Usuario usuario = request.getAction().equals("2")? usuarioRepository.findByEmail(request.getEmail()) : usuarioRepository.findByEmailAndEstatus(request.getEmail(), "1");


                    switch (request.getAction()){
                        case "0":
                            if (usuario == null) {
                                String new_user_password = managerToken.generateRandomPassword(15);
                                Usuario new_user = new Usuario(null,request.getEmail(),passwordEncrypter.cryptWithMD5(new_user_password)
                                        ,request.getName(),request.getLastName(),"2",
                                        null,null,null,"1",new Date(),null,null);
                                usuarioService.saveUsuario(true,new_user);
//                                usuarioRepository.save(new_user);
                                return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                        new SetUsuarioResponse(request.getAction(),request.getName(),request.getLastName(),request.getEmail(),request.getBlock(),new_user_password
                                        ));
                            } else
                                return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo electrónico ya esta en uso.", true, null);
                        case "1":
                            if(usuario != null){
                                usuario.setApellido(request.getLastName());
                                usuario.setNombre(request.getName());
                                usuario.setEmail(request.getEmail());
                                usuarioService.saveUsuario(false,usuario);
//                                usuarioRepository.save(usuario);

                                return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                        new SetUsuarioResponse(request.getAction(),request.getName(),request.getLastName(),request.getEmail(),request.getBlock(),null
                                        ));


                            }else
                                return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo no se encuentra en la base de Datos.", true, null);

                        case "2":
                            if(usuario != null){
                                usuario.setEstatus(request.getBlock() ? "0" : "1");
                                usuarioService.saveUsuario(false,usuario);
//                                usuarioRepository.save(usuario);

                                return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                        new SetUsuarioResponse(request.getAction(),request.getName(),request.getLastName(),request.getEmail(),request.getBlock(),null
                                        ));


                            }else
                                return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo no se encuentra en la base de Datos.", true, null);
                        case "3":
                            /**
                             * Se puede usar este fragmento y hacer uso de este servicio para la creacion de posteriores usuarios.                    *
                             *
                             * **/
                            break;
                    }
            }
        } catch (Exception e) {
            return new SetUsuarioReponseDTO(Long.valueOf(200), "Error obteniendo el usuario solicitante", true, null);
        }
        return new SetUsuarioReponseDTO(Long.valueOf(200), "Error no manejado del servidor.", true, null);

    }
}
