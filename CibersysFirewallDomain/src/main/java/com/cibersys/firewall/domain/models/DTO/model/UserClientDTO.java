package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by AKDESK25 on 8/25/2017.
 */
@AllArgsConstructor
@Data
public class UserClientDTO {
    protected Long idUsuario;
    protected String name;
    protected String lastName;
    protected String email;
    protected String ipAddress;
    protected Long idCliente;
    protected Long idUsuarioCreacion;
    protected String fechaCreacion;
    protected String estatus;


    @JsonProperty("idCliente")
    public Long getIdCliente() {
        return idCliente;
    }

    @JsonProperty("idUsuarioCreacion")
    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    @JsonProperty("fechaCreacion")
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    @JsonProperty("estatus")
    public String getEstatus() {
        return estatus;
    }

    @JsonProperty("id")
    public Long getIdUsuario() {
        return idUsuario;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("ip_address")
    public String getIpAddress() {
        return ipAddress;
    }
}
