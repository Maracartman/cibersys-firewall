package com.cibersys.firewall.domain.models.DTO.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by AKDESK04 on 3/29/2017.
 */
public class UserDTO {
    private Long id;
    private String userName, password;
    private Integer idRol;

    private String name,lastName;

    public UserDTO(){}

    public UserDTO(String userName, String password, Integer idRol) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.idRol = idRol;
    }

    public UserDTO(String s, String s1) {
        this.userName = s;
        this.password = s1;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("rol")
    public Integer getIdRol() {
        return idRol;
    }

    @JsonProperty("email") /**En versiónes anteriores esté era user**/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
