package com.cibersys.firewall.domain.models.DTO.responseDTO;

import com.cibersys.firewall.domain.models.DTO.RequestDTO.SetUsuarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Maracartman on 28/7/2017.
 */
@Data
public class SetUsuarioResponse extends SetUsuarioRequestDTO {
    private String password;
    private Boolean edited_mail;

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public SetUsuarioResponse() {
    }

    @JsonProperty("edited_mail")
    public Boolean getEdited_mail() {
        return edited_mail;
    }

    @Override
    @JsonIgnore
    public String getAction() {
        return super.getAction();
    }

    @Override
    @JsonIgnore
    public Boolean getBlock() {
        return super.getBlock();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public SetUsuarioResponse(String isNew, String name, String lastName, String email, Boolean block, String password) {
        super(isNew, name, lastName, email, block);
        this.password = password;
    }

    public SetUsuarioResponse(String isNew,Long id,String name, String lastName, String email, Boolean block, String password) {
        super(isNew,id, name, lastName, email, block);
        this.password = password;
    }

    public SetUsuarioResponse(String action, String name, String lastName, String email, Boolean block, String nonEncryptedPassword
            , Long idUsuario, String password, Boolean edited_mail) {
        super(action, name, lastName, email, block, nonEncryptedPassword, idUsuario);
        this.password = password;
        this.edited_mail = edited_mail;
    }

    public SetUsuarioResponse(String action, String name, String lastName, String email, Boolean block, String password, Boolean edited_mail) {
        super(action, name, lastName, email, block);
        this.password = password;
        this.edited_mail = edited_mail;
    }

    public SetUsuarioResponse(String action, Long id, String name, String lastName, String email, Boolean block, String password, Boolean edited_mail) {
        super(action, id, name, lastName, email, block);
        this.password = password;
        this.edited_mail = edited_mail;
    }

    public SetUsuarioResponse(String password, Boolean edited_mail) {
        this.password = password;
        this.edited_mail = edited_mail;
    }
}
