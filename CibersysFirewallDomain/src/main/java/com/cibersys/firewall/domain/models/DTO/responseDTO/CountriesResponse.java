package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maracartman on 7/8/2017.
 */
@Data
@AllArgsConstructor
public class CountriesResponse {

    private Long idPais;
    private String iso,nombre;
    private Integer codigo_telefono;


    @JsonProperty("id")
    public Long getIdPais() {
        return idPais;
    }
    @JsonProperty("iso")
    public String getIso() {
        return iso;
    }
    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }
    @JsonProperty("codigo_telefono")
    public Integer getCodigo_telefono() {
        return codigo_telefono;
    }
}
