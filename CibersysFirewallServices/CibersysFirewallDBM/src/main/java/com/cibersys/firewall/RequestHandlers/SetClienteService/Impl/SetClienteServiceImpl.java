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
public class SetClienteServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody,NewPanelClientRequestDTO>
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
        tokenUtils = new TokenUtils(secret,expiration);
        Usuario requester_user = usuarioService.
                getUserByEmail(tokenUtils.getUserFromToken(header.get("requester_user")).getUserName());
        ClientDTO client =  body.getClientInfo();
        UserInfoDTO user = body.getUserInfo();
        Pais pais = client != null ? paisService.getPaisById(client.getPais()) : null;

        switch (body.getAction()){
            case "0":
                if(client == null || user == null || pais == null)
                return new NewPanelClientResponseDTO(Long.valueOf(200),"Falta información de cliente, pais o de usuario.",
                        true,null);
                else{
                    Cliente new_client = new Cliente(null,null,client.getDireccion(),client.getTelefono(),
                            null,null,client.getNombreEmpresa(),"1",new Date(),new Date(),requester_user,requester_user,
                            pais);
                    if(usuarioService.getUserByEmail(user.getUserName()) == null){
                        Cliente saved_client =  clientService.guardarCliente(new_client);
                        if(saved_client != null){
                            Usuario client_administrator = new Usuario(null,user.getUserName(),
                                    passwordEncrypter.cryptWithMD5(managerToken.generateRandomPassword(15)),
                                    user.getName(),user.getLastName(),"3",null,null,
                                    null,"1",new Date(),null,saved_client);
                            client_administrator = usuarioService.saveUsuario(true,client_administrator);

                            return client_administrator != null ? new NewPanelClientResponseDTO(Long.valueOf(200),"Éxito en el envío de los datos.",
                                    false,body): new NewPanelClientResponseDTO(Long.valueOf(200),"Error al guardar el usuario.",
                                    true,null);

                        }else{
                            return new NewPanelClientResponseDTO(Long.valueOf(200),"Error al guardar cliente, verificar los datos.",
                                    true,null);
                        }
                    }else
                        return new NewPanelClientResponseDTO(Long.valueOf(200),"Error al guardar cliente, el usuario ya existe.",
                                true,null);
                }
            case "1":
                if(client == null || user == null || pais == null)
                    return new NewPanelClientResponseDTO(Long.valueOf(200),"Falta información de cliente, pais o de usuario.",
                            true,null);
                else{
                    Usuario editing_user = usuarioService.getUserByEmail(user
                            .getUserName());
                    if(editing_user == null){
                        Cliente editing_client =
                                clientService.getClientByUsuarioActivacionAndEstaus(editing_user,"1");
                        if(editing_client != null){

                        }else  return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "El cliente no se ha encontrado.",
                                true,null);

                    }else
                        return new NewPanelClientResponseDTO(Long.valueOf(200),
                                "El usuario introducido ya existe.",
                                true,null);



                }
            case "2":
            case "3":


        }
        return null;
    }
}
