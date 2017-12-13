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


    @Override
    public String getAction() {
        return action;
    }

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



    public SetUsuarioResponse(String isNew, String name, String lastName, String email, Boolean block, String password) {
        super(isNew, name, lastName, email, block);
        this.password = password;
    }

    public SetUsuarioResponse(String isNew,Long id,String name, String lastName, String email, Boolean block, String password) {
        super(isNew,id, name, lastName, email, block);
        this.password = password;
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
