package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maracartman on 31/7/2017.
 */
@AllArgsConstructor
@Data
public class ClientDTO {

    private String nombreEmpresa, direccion, telefono;
    private Long id, pais;


    public ClientDTO() {
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

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

    @JsonProperty("pais")
    public Long getPais() {
        return pais;
    }
}
