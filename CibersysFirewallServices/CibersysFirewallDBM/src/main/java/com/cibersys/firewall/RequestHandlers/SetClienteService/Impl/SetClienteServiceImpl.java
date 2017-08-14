package com.cibersys.firewall.RequestHandlers.SetClienteService.Impl;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Pais;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.Services.ClientService;
import com.cibersys.firewall.Repositories.Services.PaisService;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.RequestHandlers.SetClienteService.SetClienteService;
import com.cibersys.firewall.Utilities.DBMServices;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPanelClientResponseDTO;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by AKDESK25 on 8/1/2017.
 */
@Service
public class SetClienteServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody, NewPanelClientRequestDTO>
        implements SetClienteService {

    @Autowired
    private DBMServices services;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PaisService paisService;

    @Override
    public AbstractResponseBody proceedRequest(NewPanelClientRequestDTO body, Map<String, String> header) {
        /**
         * Las acciones son:
         * 0 : Crear Cliente
         * 1 : Modificar Cliente
         * 2 : Bloquear Cliente
         * 3 : Ver Información Cliente
         * **/
        tokenUtils = new TokenUtils(secret, expiration);
        UserDTO requester_userDTO = tokenUtils.getUserFromToken(header.get("requester_user"));
        Usuario requester_user = usuarioService.
                getUserByEmail(requester_userDTO.getUserName());
        ClientDTO client = body.getClientInfo();
        UserInfoDTO user = body.getUserInfo();
        Pais pais = client != null ? paisService.getPaisById(client.getPais()) : null;

        switch (body.getAction()) {
            case "0":
                if (client == null || user == null || pais == null)
                    return new NewPanelClientResponseDTO(Long.valueOf(200), "Falta información de cliente, pais o de usuario.",
                            true, null);
                else {

                    if (user.getName() != null && user.getLastName() != null && user.getUserName() != null) {
                        /**
                         *
                         * Procedimiento de usuario
                         *
                         *
                         *
                         * **/
                        if (user.getUserId() == null || usuarioService.find(user.getUserId()) == null) {
                            String new_random_password = managerToken.generateRandomPassword(15);
                            Usuario user_administrator = new Usuario(null, user.getUserName(),
                                    passwordEncrypter.cryptWithMD5(new_random_password),
                                    user.getName(), user.getLastName(), "3", null, null,
                                    null, "1", new Date(), null, null);
                            user_administrator = usuarioService.saveUsuario(true, user_administrator);
                            body.getUserInfo().setPassword(new_random_password);
                            if (user_administrator != null) {
                                /**
                                 *
                                 * Procedimiento de cliente
                                 *
                                 *
                                 *
                                 * **/
                                Cliente new_client = new Cliente(null, null, client.getDireccion(), client.getTelefono(),
                                        null, null, client.getNombreEmpresa(), "1", new Date(), new Date(),
                                        requester_user, requester_user,
                                        pais);
                                Cliente saved_client = clientService.guardarCliente(new_client);
                                if (saved_client != null) {
                                    user_administrator.setCliente(saved_client);
                                    usuarioService.saveUsuario(false, user_administrator);
                                    body.getUserInfo().setEdited_mail(true);
                                    return new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                            false, Arrays.asList(body));
                                } else {
                                    return new NewPanelClientResponseDTO(Long.valueOf(200), "Error al guardar cliente, verificar los datos.",
                                            true, null);
                                }

                            } else
                                return new NewPanelClientResponseDTO(Long.valueOf(200), "Error al guardar el usuario.",
                                        true, null);

                        } else
                            return new NewPanelClientResponseDTO(Long.valueOf(200), "El usuario ya existe.",
                                    true, null);


                    } else return new NewPanelClientResponseDTO(Long.valueOf(200), "Falta información del usuario.",
                            true, null);
                }
            case "1":
                if (client == null)
                    return new NewPanelClientResponseDTO(Long.valueOf(200), "Debe haber información del cliente para continuar.",
                            true, null);
                else if (client.getId() == null)
                    return new NewPanelClientResponseDTO(Long.valueOf(200), "El id del cliente no puede ser nulo.",
                            true, null);
                else {
                    Cliente editing_client =
                            clientService.getCLientById(client.getId());
                    if (editing_client != null) {
                        Usuario editing_user = usuarioService.getClientAdministratorByCliente(editing_client);
                        /**
                         * Seteamos los valores nuevos de cliente y de usuario
                         *
                         * **/
                        if (client.getNombreEmpresa() != null) editing_client.setNombre(client.getNombreEmpresa());
                        if (client.getDireccion() != null) editing_client.setDireccion(client.getDireccion());
                        if (client.getDireccion() != null) editing_client.setTelefono_1(client.getTelefono());
                        if (client.getPais() != null) editing_client.setPais(pais);

                        editing_client.setUsuarioActualizacion(requester_user);
                        editing_client.setFecha_actualizacion(new Date());

                        if (user != null) {
                            if (user.getName() != null) editing_user.setNombre(user.getName());
                            if (user.getLastName() != null) editing_user.setApellido(user.getLastName());
                            if (user.getUserName() != null && !user.getUserName().equalsIgnoreCase(editing_user.getEmail())) {
                                if (usuarioService.getUserByEmail(user.getUserName()) == null) {
                                    String new_random_password = managerToken.generateRandomPassword(15);
                                    editing_user.setEmail(user.getUserName());
                                    editing_user.setCodigoValidacion(null);
                                    editing_user.setFechaCodigoValidacion(null);
                                    editing_user.setContraseña(passwordEncrypter.cryptWithMD5(new_random_password));

                                    body.getUserInfo().setPassword(new_random_password);
                                    body.getUserInfo().setEdited_mail(true);
                                } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                                        "No se há podido guardar el cliente, el correo electrónico ya se encuentra en la Base de " +
                                                "datos.",
                                        true, null);

                            } else body.getUserInfo().setEdited_mail(false);
                        } else {
                            UserInfoDTO consult = new UserInfoDTO(editing_user.getEmail(), null,
                                    Integer.valueOf(editing_user.getRol()), editing_user.getNombre(), editing_user.getApellido(), null, editing_user.getIdusuario(),
                                    editing_user.getEstatus().equals("1") ? false : true);
                            body.setUserInfo(consult);
                        }
                        editing_client = clientService.guardarCliente(editing_client);
                        if (editing_client != null) {
                            editing_user = usuarioService.saveUsuario(false, editing_user);
                            if (editing_user != null)
                                return new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                        false, Arrays.asList(body));
                            else
                                return new NewPanelClientResponseDTO(Long.valueOf(200),
                                        "Há ocurrido un error guardando el usuario.",
                                        true, null);

                        } else
                            return new NewPanelClientResponseDTO(Long.valueOf(200),
                                    "Há ocurrido un error guardando el cliente.",
                                    true, null);

                    } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "El cliente no se ha encontrado.",
                            true, null);
                }
            case "2":
                if (client != null && client.getId() != null) {
                    if (body.getBlock() != null) {
                        Cliente client_to_block = clientService.getCLientById(client.getId());
                        if (client_to_block != null) {
                                if ((body.getBlock() && client_to_block.getEstatus().equalsIgnoreCase("0"))
                                        || (!body.getBlock() && client_to_block.getEstatus().equalsIgnoreCase("1"))){
                                   String blocking = "bloqueado.";
                                   if(!body.getBlock())
                                       blocking = "desbloqueado.";
                                    return new NewPanelClientResponseDTO(Long.valueOf(200),
                                            "El usuario ya se encuentra "+blocking,
                                            true, null);
                                }

                                else {
                                    client_to_block.setEstatus(body.getBlock() ? "0" : "1");
                                    client_to_block.setUsuarioActualizacion(requester_user);
                                    client_to_block.setFecha_actualizacion(new Date());
                                    clientService.guardarCliente(client_to_block);
                                    for (Usuario u : usuarioService.getAllUsuarioByCliente(client_to_block)) {
                                    if (body.getBlock())
                                        u.setEstatus("0");
                                    else
                                        u.setEstatus("1");
                                    /**
                                     * Guardamos los cambios
                                     *
                                     * **/
                                    usuarioService.saveUsuario(false,u);
                                }
                                    return new NewPanelClientResponseDTO(Long.valueOf(200),
                                            "Éxito en el envío de los datos.",
                                            false, null);
                            }
                        } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Ha ocurrido un error obteniendo la información de la BD.",
                                true, null);
                    } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "Debe indicar si desea bloquear o no el cliente (block).",
                            true, null);

                } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                        "Debe ingresar la información (ID) del cliente a bloquear.",
                        true, null);
            case "3":
                if (client != null && client.getId() != null) {
                    Cliente consulting_client = clientService.getCLientById(client.getId());
                    if (consulting_client != null) {
                        Usuario consulting_user = usuarioService.getClientAdministratorByCliente(consulting_client);
                        /**
                         *
                         * Cargamos la lista de usuarios del cliente
                         *
                         * **/
                        List<Usuario> usuarios_x_cliente = usuarioService.getAllUsuarioByRolAndCliente("4", consulting_client);
                        List<UserInfoDTO> sur = new ArrayList<>();
                        for (Usuario u : usuarios_x_cliente) {
                            sur.add(services.mapToUserInfoDTO(u));
                        }
                        return new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                false, Arrays.asList(new NewPanelClientRequestDTO(new ClientDTO(consulting_client.getNombre(),
                                consulting_client.getDireccion(), consulting_client.getTelefono1(), consulting_client.getIdcliente()
                                , consulting_client.getPais().getIdpais(),consulting_client.getFechaActivacion().toString()), new UserInfoDTO(consulting_user.getEmail(), null,
                                Integer.parseInt(consulting_user.getRol()),
                                consulting_user.getNombre(), consulting_user.getApellido(),
                                false, consulting_user.getIdusuario(), consulting_user.getEstatus().equals("1") ? false : true), sur, "3",
                                consulting_client.getEstatus().equals("1") ? false : true)));

                    } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "El cliente no se ha encontrado.",
                            true, null);
                } else {
                    try {
                        List<NewPanelClientRequestDTO> listOfPanelClients = new ArrayList<NewPanelClientRequestDTO>();
                        for (Cliente c : clientService.getAllClientes()) {
                            ClientDTO dto = services.mapToClienteDTO(c);
                            UserInfoDTO uidto = services.mapToUserInfoDTO(usuarioService.getClientAdministratorByCliente(c));
                            /**
                             *
                             * Cargamos la lista de usuarios del cliente
                             *
                             * **/
                            List<Usuario> usuarios_x_cliente = usuarioService.getAllUsuarioByRolAndCliente("4", c);
                            List<UserInfoDTO> sur = new ArrayList<>();
                            for (Usuario u : usuarios_x_cliente) {
                                sur.add(services.mapToUserInfoDTO(u));
                            }
                            listOfPanelClients.add(new NewPanelClientRequestDTO(dto, uidto, sur, "3", c.getEstatus().equals("1") ? false : true));
                        }
                        return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Éxito en el envío de los datos.",
                                false, listOfPanelClients);
                    } catch (Exception e) {
                        return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Há ocurrido un error consultando el listado de clientes.",
                                true, null);
                    }


                }
        }
        return null;
    }
}
