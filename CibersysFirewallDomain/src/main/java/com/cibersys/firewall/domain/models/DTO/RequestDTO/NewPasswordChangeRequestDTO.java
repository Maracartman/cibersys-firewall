package com.cibersys.firewall.domain.models.DTO.RequestDTO;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Luis Maracara on 6/21/2017.
 */
public class NewPasswordChangeRequestDTO {

    private String email;

    private String validationCode;

    private String nombre;

    private String apellido;

    private String rol;

    private String imagen_url;

    private String confirmPassword;

    public NewPasswordChangeRequestDTO(){}

    public NewPasswordChangeRequestDTO(String email, String validationCode, String nombre, String apellido, String rol, String imagen_url) {
        this.email = email;
        this.validationCode = validationCode;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.imagen_url = imagen_url;
    }
    public NewPasswordChangeRequestDTO(String email, String validationCode) {
        this.email = email;
        this.validationCode = validationCode;
        this.nombre = "";
        this.apellido = "";
        this.rol = "";
        this.imagen_url = "";
    }

    @JsonProperty(value = "confirmPassword")
    public String getConfirmPassword() {
        return confirmPassword;
    }


    @JsonProperty(value = "nombre")
    public String getNombre() {
        return nombre;
    }
    @JsonProperty(value = "apellido")
    public String getApellido() {
        return apellido;
    }
    @JsonProperty(value = "rol")
    public String getRol() {
        return rol;
    }
    @JsonProperty(value = "imagen_url")
    public String getImagen_url() {
        return imagen_url;
    }

    @JsonProperty(value = "email")
    public String getEmail() {
        return email;
    }

    @JsonProperty(value = "validationCode")
    public String getValidationCode() {
        return validationCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
