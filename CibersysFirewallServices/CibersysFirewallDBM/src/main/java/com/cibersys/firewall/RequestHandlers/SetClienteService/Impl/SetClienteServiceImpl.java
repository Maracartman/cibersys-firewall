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
        Usuario requester_user = usuarioService.
                getUserByEmail(tokenUtils.getUserFromToken(header.get("requester_user")).getUserName());
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
                        Usuario editing_user = usuarioService.getUserByCliente(editing_client);
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
                                String new_random_password = managerToken.generateRandomPassword(15);
                                editing_user.setEmail(user.getUserName());
                                editing_user.setCodigoValidacion(null);
                                editing_user.setFechaCodigoValidacion(null);
                                editing_user.setContraseña(passwordEncrypter.cryptWithMD5(new_random_password));

                                body.getUserInfo().setPassword(new_random_password);
                                body.getUserInfo().setEdited_mail(true);
                            } else body.getUserInfo().setEdited_mail(false);
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
                if(client != null && client.getId() != null){
                    if(body.getBlock() != null){
                        Cliente client_to_block = clientService.getCLientById(client.getId());
                        Usuario user_to_block = usuarioService.getUserByCliente(client_to_block);
                        if(client_to_block != null && user_to_block != null){
                            if((body.getBlock() && client_to_block.getEstatus().equalsIgnoreCase("0"))
                                    || (!body.getBlock() && client_to_block.getEstatus().equalsIgnoreCase("1")))
                                return  new NewPanelClientResponseDTO(Long.valueOf(200),
                                        body.getBlock() ? "El cliente ya se encuentra bloqueado." : "El cliente ya se encuentra desbloqueado.",
                                        true, null);
                            else{
                                if(body.getBlock()){
                                    client_to_block.setEstatus("0");
                                    user_to_block.setEstatus("0");
                                }else{
                                    client_to_block.setEstatus("1");
                                    user_to_block.setEstatus("1");
                                }
                                client_to_block.setUsuarioActualizacion(requester_user);
                                client_to_block.setFecha_actualizacion(new Date());

                                /**
                                 * Guardamos los cambios
                                 *
                                 * **/
                                clientService.guardarCliente(client_to_block);
                                usuarioService.saveUsuario(false,user_to_block);

                                return new NewPanelClientResponseDTO(Long.valueOf(200),
                                        "Éxito en el envío de los datos.",
                                        true, null);

                            }

                        }else return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Ha ocurrido un error obteniendo la información de la BD.",
                                true, null);
                    }else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "Debe indicar si desea bloquear o no el cliente (block).",
                            true, null);

                } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                        "Debe ingresar la información (ID) del cliente a bloquear.",
                        true, null);

            case "3":
                if (client != null && client.getId() != null) {
                    Cliente consulting_client = clientService.getCLientById(client.getId());
                    if (consulting_client != null) {
                        Usuario consulting_user = usuarioService.getUserByCliente(consulting_client);
                        return new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                false, Arrays.asList(new NewPanelClientRequestDTO(new ClientDTO(consulting_client.getNombre(),
                                consulting_client.getDireccion(), consulting_client.getTelefono1(), consulting_client.getIdcliente()
                                , consulting_client.getPais().getIdpais()), new UserInfoDTO(consulting_user.getEmail(), null,
                                Integer.parseInt(consulting_user.getRol()),
                                consulting_user.getNombre(), consulting_user.getApellido(),
                                false,consulting_user.getIdusuario()), "3", null)));
                    } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "El cliente no se ha encontrado.",
                            true, null);
                } else {
                    try{
                        List<NewPanelClientRequestDTO> listOfPanelClients = new ArrayList<NewPanelClientRequestDTO>();
                        for(Cliente c : clientService.getAllClientes()){
                            ClientDTO dto = services.mapToClienteDTO(c);
                            UserInfoDTO uidto = services.mapToUserInfoDTO(usuarioService.getUserByCliente(c));
                            listOfPanelClients.add(new NewPanelClientRequestDTO(dto,uidto,"3",null));
                        }
                        return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Éxito en el envío de los datos.",
                                false,listOfPanelClients);
                    }catch (Exception e){
                        return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "Há ocurrido un error consultando el listado de clientes.",
                                true,null);
                    }


                }
        }
        return null;
    }
}
