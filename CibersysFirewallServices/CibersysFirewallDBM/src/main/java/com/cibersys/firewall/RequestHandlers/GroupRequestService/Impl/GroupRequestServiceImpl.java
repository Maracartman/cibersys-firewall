package com.cibersys.firewall.RequestHandlers.GroupRequestService.Impl;

import com.cibersys.firewall.Domain.Model.Grupo;
import com.cibersys.firewall.Domain.Model.GrupoUsuarioCliente;
import com.cibersys.firewall.Domain.Model.UsuarioCliente;
import com.cibersys.firewall.Repositories.Services.GrupoService;
import com.cibersys.firewall.Repositories.Services.GrupoUsuarioClienteService;
import com.cibersys.firewall.Repositories.Services.UsuarioClienteService;
import com.cibersys.firewall.Repositories.Services.UsuarioService;
import com.cibersys.firewall.RequestHandlers.AbstractHandler.Impl.AbstractPrivateRequestHandlerServiceImpl;
import com.cibersys.firewall.RequestHandlers.GroupRequestService.GroupRequestService;
import com.cibersys.firewall.Utilities.DBMServices;
import com.cibersys.firewall.domain.models.DTO.RequestDTO.GroupRequestDTO;
import com.cibersys.firewall.domain.models.DTO.ResponseBody.AbstractResponseBody;
import com.cibersys.firewall.domain.models.DTO.responseDTO.GroupResponseDTO;
import com.cibersys.firewall.domain.models.DTO.responseDTO.ResponseError;
import com.cibersys.firewall.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Service
public class GroupRequestServiceImpl extends AbstractPrivateRequestHandlerServiceImpl<AbstractResponseBody, GroupRequestDTO> implements GroupRequestService {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoUsuarioClienteService grupoUsuarioClienteService;

    @Autowired
    private UsuarioClienteService usuarioClienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DBMServices dbmServices;

    @Override
    public AbstractResponseBody proceedRequest(GroupRequestDTO body, Map<String, String> header) {
        tokenUtils = new TokenUtils(secret, expiration);
        if (body.getAction() != null && body.getGroupInfo() != null) {
            switch (body.getAction()) {
                case "0": {
                    /**
                     * caso crear
                     * **/
                    Grupo grupo = dbmServices.mapToGrupo(body.getGroupInfo());
                    grupo.setEstatus("1");
                    grupo.setFechaCreacion(new Date());
                    grupo.setUsuarioCreacion(usuarioService.getUserByEmail(tokenUtils.getUserFromToken(header.get("requester_user")).getUserName()));
                    /**
                     * Guardamos el grupo
                     * **/
                    Grupo save = grupoService.guardarGrupo(grupo);
                    if( save != null){
                        /**
                         * Consultamos todos los clientes solicitados para ser asignados al grupo
                         * **/
                        List<UsuarioCliente> usuariosClientes = new ArrayList<>();
                        if(body.getUserClients() != null){
                            ArrayList<Long> array = new ArrayList<>();
                            body.getUserClients().forEach(userClientDTO -> array.add(userClientDTO.getIdUsuario()));
                            usuariosClientes = usuarioClienteService.obtenerUsuariosClientePorGrupo(array);
                            if(usuariosClientes != null){
                                /**
                                 * Creamos los registros de GrupoUsuarioCliente.
                                 * **/
                            usuariosClientes.forEach(uc -> {
                                grupoUsuarioClienteService.guardarGrupoUsuarioCliente(new GrupoUsuarioCliente(null,uc,save,"1"));
                            });
                            }
                        }
                        return new GroupResponseDTO(Long.valueOf(200),"",false, Arrays.asList(
                                new GroupRequestDTO(null,null,dbmServices.mapToGroupDTO(save),
                                        dbmServices.mapToUserClientDTOList(usuariosClientes))));
                    }else  return new ResponseError(Long.valueOf(200), "Error al guardar el grupo, revisar los parámetros.", true, null);
                }
                case "1": {
                    /**
                     * Caso Modificar
                     * **/
                }
                case "3": {
                    /**
                     * Caso consulta
                     * **/
                if(body.getIdCliente() != null ){
                    /**
                     * Caso para Administrador General dado un cliente retorna la informacion de sus grupos.
                     * (Falta agregar indentificador de CLIENTE EN GRUPO)
                     * **/
                }else {
                    /**
                     * Se toma por el usuario logeado (Token)
                     * **/
                    List<Grupo> gruposPorCliente = grupoService.getAllGroupsByUsuarioCreacion(
                            usuarioService.getUserByEmail(
                                    tokenUtils.getUserFromToken(header.get("requester_user")).getUserName()));
                    if(gruposPorCliente != null){
                        List<GroupRequestDTO> list = new ArrayList<>();
                        gruposPorCliente.forEach(grupo -> {
                            list.add(new GroupRequestDTO(null,null,dbmServices.mapToGroupDTO(grupo),dbmServices.mapToUserClientDTOList(grupo.getUserClients())));
                        });
                        return new GroupResponseDTO(Long.valueOf(200),"",false,
                                list);

                    }else return new ResponseError(Long.valueOf(200), "El cliente no tiene grupos", false, null);
                }
                }
                default:
                    return new ResponseError(Long.valueOf(200), "Accion no controlada por el servicio o no disponible", true, null);
            }
        } else return new ResponseError(Long.valueOf(200), "Falta parámetros" +
                "en el request", true, null);
    }


}
