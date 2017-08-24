package com.cibersys.firewall.RequestHandlers.SetUsuarioService.Impl;


import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import com.cibersys.firewall.Repositories.Services.ClientService;
import com.cibersys.firewall.Repositories.Services.UsuarioClienteService;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.Repositories.UsuarioRepository;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractRequestHandler;
import com.cibersys.firewall.RequestHandlers.SetUsuarioService.SetUsuarioService;
import com.cibersys.firewall.Utilities.ManagerToken;
import com.cibersys.firewall.converter.PasswordEncrypter;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioReponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.SetUsuarioResponse;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    private UsuarioClienteService usuarioClienteService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Boolean _edited = false;

    private String new_random_password;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

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

            /**
             * El que registra es un administrador (1)
             * 2. El usuario otro usuario Cibersys (rol igual a 2). Este es un usuario Cibersys que se crea para que atienda las solicitudes de los clientes y es creado exclusivamente por el administrador Cibersys (rol igual a 1).
             3. El usuario administrador Cliente (rol igual a 3). Este es la persona de contacto del cliente y se crea cuando creamos al cliente. Este usuario es creado exclusivamente por el administrador Cibersys (rol igual a 1).
             * **/
            Usuario usuario = null;
            if (request.getIdUsuario() != null && !request.getAction().equals("0"))
                usuario = usuarioService.getUserById(request.getIdUsuario());
            else if (request.getEmail() != null)
                usuario = usuarioService.getUserByEmail(request.getEmail());
            switch (request.getAction()) {
                case "0":
                    if (usuario == null) {
                        if(request.getUserClient() != null && request.getUserClient()){
                            if(requester_user.getIdRol() == 3 || requester_user.getIdRol() == 4){
                                Usuario creador = usuarioService.getUserByEmail(requester_user.getUserName());

                                UsuarioCliente usuarioCliente = new UsuarioCliente(null,request.getName(),
                                        request.getLastName(),request.getEmail(),request.getIpAddress(),
                                        creador.getCliente(),creador, new Date(),"1");
                                UsuarioCliente result = usuarioClienteService.guardarUsuarioCliente(usuarioCliente);

                                return  result != null ?
                                        new SetUsuarioReponseDTO(Long.valueOf(200),
                                                "Éxito en el envío de los datos.", false,
                                        Arrays.asList(new SetUsuarioResponse(request.getAction(),
                                                result.getIdUsuarioCliente(),
                                                request.getName(), request.getLastName(),
                                                request.getEmail(), false, null
                                        ))) : new SetUsuarioReponseDTO(Long.valueOf(200),
                                        "Ha ocurrido un error guardando los datos verifique y envie nuevamente.", true, null);
                            }else
                                return new SetUsuarioReponseDTO(Long.valueOf(200),
                                        "No posee los permisos para crear este tipo de usuario.", true, null);

                        }else{
                            String new_user_password = managerToken.generateRandomPassword(15);
                            Usuario new_user = new Usuario(null, request.getEmail(), passwordEncrypter.cryptWithMD5(new_user_password)
                                    , request.getName(), request.getLastName(), requester_user.getIdRol() == 1 ? "2" : "4",
                                    null, null, null, "1", new Date(), null, requester_user.getIdRol() != 3 ? null :
                                    usuarioService.getUserByEmail(requester_user.getUserName()).getCliente());
                            new_user = usuarioService.saveUsuario(true, new_user);
                            return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                    Arrays.asList(new SetUsuarioResponse(request.getAction(),
                                            new_user.getIdusuario(),
                                            request.getName(), request.getLastName(),
                                            request.getEmail(), false, new_user_password
                                    )));
                        }

                    } else
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo electrónico ya esta en uso.", true, null);
                case "1":
                    _edited = false;
                    new_random_password = "";
                    if (usuario != null) {
                        if (request.getLastName() != null) usuario.setApellido(request.getLastName());
                        if (request.getName() != null) usuario.setNombre(request.getName());
                        if (request.getEmail() != null) {
                            if (!usuario.getEmail().equals(request.getEmail())) {
                                if (usuarioService.getUserByEmail(request.getEmail()) == null) {
                                    _edited = true;
                                    usuario.setEmail(request.getEmail());
                                    new_random_password = managerToken.generateRandomPassword(15);
                                    usuario.setCodigoValidacion(null);
                                    usuario.setFechaCodigoValidacion(null);
                                    usuario.setContraseña(passwordEncrypter.cryptWithMD5(new_random_password));
                                } else
                                    return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo ya se encuentra en la base de Datos.",
                                            true, null);
                            }
                        }
                        usuario = usuarioService.saveUsuario(false, usuario);
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                Arrays.asList(new SetUsuarioResponse(request.getAction(), usuario.getIdusuario(), usuario.getNombre(),
                                        usuario.getApellido(), usuario.getEmail(), usuario.getEstatus().equals("1") ? false : true,
                                        _edited ? new_random_password : null, _edited)));


                    } else
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "El usuario no se encuentra en la base de Datos.", true, null);

                case "2":
                    if (usuario != null) {
                        usuario.setEstatus(request.getBlock() ? "0" : "1");
                        usuario = usuarioService.saveUsuario(false, usuario);
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.", false,
                                Arrays.asList(new SetUsuarioResponse(request.getAction(),
                                        usuario.getNombre(), usuario.getApellido(),
                                        usuario.getEmail(), usuario.getEstatus().equals("1") ? false : true, null
                                )));


                    } else
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "El correo no se encuentra en la base de Datos.", true, null);
                case "3":
                    /**
                     * Se puede usar este fragmento y hacer uso de este servicio para la creacion de posteriores usuarios.                    *
                     *
                     * **/
                    if (usuario != null)
                        return new SetUsuarioReponseDTO(Long.valueOf(200), "", false, Arrays.asList(new SetUsuarioResponse(null, usuario.getIdusuario(),
                                usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getEstatus().equals("1") ? false : true, null)));
                    else {
                        List<Usuario> users;
                        users = requester_user.getIdRol() == 1 ? usuarioService.getAllUsuariosByRol("2") : usuarioService.getAllUsuarioByRolAndCliente("4",
                                usuarioService.getUserByEmail(requester_user.getUserName()).getCliente());
                        List<SetUsuarioResponse> sur = new ArrayList<>();
                        if (users != null)
                            sur.addAll(users.stream().map(u -> new SetUsuarioResponse(null, u.getIdusuario(), u.getNombre(),
                                    u.getApellido(), u.getEmail(), u.getEstatus().equals("1") ? false : true, null)).collect(Collectors.toList()));
                        return new SetUsuarioReponseDTO(Long.valueOf(200),
                                "Éxito en el envío de los datos.", false, sur);
                    }
            }

        } catch (Exception e) {
            return new SetUsuarioReponseDTO(Long.valueOf(200), "Error obteniendo el usuario solicitante.", true, null);
        }
        return new SetUsuarioReponseDTO(Long.valueOf(200), "Error no manejado del servidor.", true, null);

    }
}
