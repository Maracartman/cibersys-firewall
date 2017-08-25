package com.cibersys.firewall.domain.models.DTO.model;

import com.cibersys.firewall.domain.models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@AllArgsConstructor
@Data
public class GroupDTO {

    @JsonProperty("group_id")
    @JsonIgnoreProperties(allowGetters = true)
    private Long idGrupo;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("description")
    private String descripcion;

    @JsonProperty("ipAddress")
    private String direccionIp;

    @JsonProperty("ipType")
    private String tipoIp;

    @JsonProperty("estatus")
    private String estatus;

    @JsonProperty("creation_date")
    private String fechaCreacion;

    @JsonProperty("creator")
    private UserDTO usuarioCreacion;
}
