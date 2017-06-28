package com.cibersys.firewall.Domain.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AKDESK25 on 6/14/2017.
 */
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="INT(11)")
    private Integer idcliente;

    private String rif,direccion,telefono_1,telefono_2,email,nombre;

    @Column(columnDefinition="CHAR(1)")
    private String estatus;

    private Date fecha_activacion,fecha_actualizacion;

    @Column(columnDefinition="INT(11)")
    private Long usuario_activacion,usuario_actualizacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpais")
    private Pais pais;


    public Integer getIdcliente() {
        return idcliente;
    }

    public String getRif() {
        return rif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public String getTelefono_2() {
        return telefono_2;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public Date getFecha_activacion() {
        return fecha_activacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public Long getUsuario_activacion() {
        return usuario_activacion;
    }

    public Long getUsuario_actualizacion() {
        return usuario_actualizacion;
    }

    public Pais getPais() {
        return pais;
    }


    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public void setTelefono_2(String telefono_2) {
        this.telefono_2 = telefono_2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setFecha_activacion(Date fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public void setUsuario_activacion(Long usuario_activacion) {
        this.usuario_activacion = usuario_activacion;
    }

    public void setUsuario_actualizacion(Long usuario_actualizacion) {
        this.usuario_actualizacion = usuario_actualizacion;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
