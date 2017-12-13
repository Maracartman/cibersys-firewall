package com.cibersys.firewall.domain.models.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maracartman on 27/7/2017.
 */
@Data
@AllArgsConstructor
public class SetUsuarioRequestDTO {

    protected String action;
    protected String name;
    protected String lastName;
    protected String email;
    protected Boolean block;
    protected String nonEncryptedPassword;
    protected Long idUsuario;

    /**
     * Mapping para Usuario cliente bajo el mismo servicio de creacion de usuario
     ***/
    protected Boolean userClient;
    protected String IpAddress;




    public SetUsuarioRequestDTO(String action, String name, String lastName, String email, Boolean block) {
        this.action = action;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.block = block;
    }

    public SetUsuarioRequestDTO(String action,Long id, String name, String lastName, String email, Boolean block) {
        this.action = action;
        this.idUsuario = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.block = block;
    }

    public SetUsuarioRequestDTO() {
    }
    /**
     * Se puede usar este campo en caso de que sea crear un usuario cliente:
     * */
    @JsonProperty("id")
    public Long getIdUsuario() {
        return idUsuario;
    }

    @JsonProperty("password")
    public String getNonEncryptedPassword(){return nonEncryptedPassword;}

    @JsonProperty("block")
    public Boolean getBlock() {
        return block;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
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
    /***
     * Para usuarios clientes
     *
     * ***/
    @JsonProperty("user_client")
    public Boolean getUserClient() {
        return userClient;
    }
    @JsonProperty("ip_address")
    public String getIpAddress() {
        return IpAddress;
    }
}
