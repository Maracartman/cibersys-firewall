package com.cibersys.firewall.Domain.Model;


import javax.persistence.*;
import java.util.Date;


/**
 * Created by AKDESK25 on 6/14/2017.
 */
@Entity
@Table(name = "usuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="INT(11)")
    private Long idusuario;

    private String email;

    private String contraseña;

    private String nombre;

    private String apellido;

    @Column(columnDefinition="CHAR(1)")
    private String rol;

    private String imagen_url;

    private String direccion_ip;

    private String codigoValidacion;

    @Column(columnDefinition="CHAR(1)")
    private String estatus;

    private Date fechaCreacion, fechaCodigoValidacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcliente")
    private Cliente cliente;


    public Long getIdusuario() {
        return idusuario;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRol() {
        return rol;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public String getDireccion_ip() {
        return direccion_ip;
    }

    @Column(name = "codigo_validacion")
    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public String getEstatus() {
        return estatus;
    }

    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    @Column(name = "fecha_codigo_validacion")
    public Date getFechaCodigoValidacion() {
        return fechaCodigoValidacion;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public void setDireccion_ip(String direccion_ip) {
        this.direccion_ip = direccion_ip;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaCodigoValidacion(Date fechaCodigoValidacion) {
        this.fechaCodigoValidacion = fechaCodigoValidacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
