package com.cibersys.firewall.RequestHandlers.SetClienteService.Impl;

import com.cibersys.firewall.Domain.Model.Cliente;
import com.cibersys.firewall.Domain.Model.Pais;
import com.cibersys.firewall.Domain.Model.Usuario;
import com.cibersys.firewall.Repositories.Services.ClientService;
import com.cibersys.firewall.Repositories.Services.PaisService;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.RequestHandlers.SetClienteService.SetClienteService;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.NewPanelClientRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.model.ClientDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserInfoDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.NewPanelClientResponseDTO;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by AKDESK25 on 8/1/2017.
 */
@Service
public class SetClienteServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody, NewPanelClientRequestDTO>
        implements SetClienteService {

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
                    Cliente new_client = new Cliente(null, null, client.getDireccion(), client.getTelefono(),
                            null, null, client.getNombreEmpresa(), "1", new Date(), new Date(), requester_user, requester_user,
                            pais);
                    if (usuarioService.getUserByEmail(user.getUserName()) == null) {
                        Cliente saved_client = clientService.guardarCliente(new_client);
                        if (saved_client != null) {
                            String new_random_password = managerToken.generateRandomPassword(15);
                            Usuario client_administrator = new Usuario(null, user.getUserName(),
                                    passwordEncrypter.cryptWithMD5(new_random_password),
                                    user.getName(), user.getLastName(), "3", null, null,
                                    null, "1", new Date(), null, saved_client);
                            client_administrator = usuarioService.saveUsuario(true, client_administrator);
                            body.getUserInfo().setPassword(new_random_password);
                            return client_administrator != null ? new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                    false, body) : new NewPanelClientResponseDTO(Long.valueOf(200), "Error al guardar el usuario.",
                                    true, null);

                        } else {
                            return new NewPanelClientResponseDTO(Long.valueOf(200), "Error al guardar cliente, verificar los datos.",
                                    true, null);
                        }
                    } else
                        return new NewPanelClientResponseDTO(Long.valueOf(200), "Error al guardar cliente, el usuario ya existe.",
                                true, null);
                }
            case "1":
                if (client == null || user == null || pais == null)
                    return new NewPanelClientResponseDTO(Long.valueOf(200), "Falta información de cliente, pais o de usuario.",
                            true, null);
                else {
                    Cliente editing_client =
                            clientService.getCLientByIdAndEstatus(client.getId(), "1");
                    if (editing_client != null) {
                        Usuario editing_user = usuarioService.find(editing_client.getUsuarioActivacion().getIdusuario());
                        if (usuarioService.getUserByEmail(user.getUserName()) == null) {
                            /**
                             * Seteamos los valores nuevos de cliente y de usuario
                             *
                             * **/
                            editing_client.setNombre(client.getNombreEmpresa());
                            editing_client.setDireccion(client.getDireccion());
                            editing_client.setTelefono_1(client.getTelefono());
                            editing_client.setPais(pais);

                            editing_user.setNombre(user.getName());
                            editing_user.setApellido(user.getLastName());
                            editing_user.setEmail(user.getUserName());

                            editing_client = clientService.guardarCliente(editing_client);
                            if (editing_client != null) {
                                editing_user = usuarioService.saveUsuario(false, editing_user);
                                if (editing_user != null)
                                    new NewPanelClientResponseDTO(Long.valueOf(200), "Éxito en el envío de los datos.",
                                            false, body);
                                else
                                    return new NewPanelClientResponseDTO(Long.valueOf(200),
                                            "Há ocurrido un error guardando el usuario.",
                                            true, null);

                            } else
                                return new NewPanelClientResponseDTO(Long.valueOf(200),
                                        "Há ocurrido un error guardando el cliente.",
                                        true, null);

                        } else
                            return new NewPanelClientResponseDTO(Long.valueOf(200),
                                    "El usuario introducido ya existe.",
                                    true, null);

                    } else return new NewPanelClientResponseDTO(Long.valueOf(200),
                            "El cliente no se ha encontrado.",
                            true, null);
                }
            case "2":
            case "3":


        }
        return null;
    }
}
