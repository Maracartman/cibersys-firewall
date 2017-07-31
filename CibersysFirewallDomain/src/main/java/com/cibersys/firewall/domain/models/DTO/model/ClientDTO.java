package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * Created by Maracartman on 31/7/2017.
 */
@AllArgsConstructor
public class ClientDTO {

    private String nombreEmpresa,direccion,telefono;

    @JsonProperty("nombreEmpresa")
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    @JsonProperty("direccion")
    public String getDireccion() {
        return direccion;
    }
    @JsonProperty("telefono")
    public String getTelefono() {
        return telefono;
    }

    public ClientDTO() {
    }
}
