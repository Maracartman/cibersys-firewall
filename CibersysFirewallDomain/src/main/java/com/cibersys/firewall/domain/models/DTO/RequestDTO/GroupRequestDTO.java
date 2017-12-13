package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.cibersys.firewall.domain.models.DTO.model.GroupDTO;
import com.cibersys.firewall.domain.models.DTO.model.UserClientDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@Data
@AllArgsConstructor
public class GroupRequestDTO {


    /**
     * Para trabajar varios procedimientos dentro de este mismo servicio:
     * @crear = "0"
     * @modificar = "1"
     * @bloquear = "2"
     * @consultar = "3"
     * @ver detalles en Diccionario de JSON Google Drive Cibersys.
     * **/
    @JsonProperty("action")
    private String action;


    @JsonProperty("idCliente")
    private Long idCliente; /**Para consultar dado un cliente los grupos**/

    @JsonProperty("groupInfo")
    private GroupDTO groupInfo;

    @JsonProperty("userClients")
    private List<UserClientDTO> userClients;
}
