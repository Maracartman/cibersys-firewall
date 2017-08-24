package com.cibersys.firewall.Domain.Model;



import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="INT(11)")
    private Long idcliente;

    private String rif,direccion,telefono_1,telefono_2,email,nombre;

    @Column(columnDefinition="CHAR(1)")
    private String estatus;

    private Date fecha_activacion,fecha_actualizacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_activacion")
    private Usuario usuarioActivacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_actualizacion")
    private Usuario usuarioActualizacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpais")
    private Pais pais;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<UsuarioCliente> listOfUserClients;


    public Cliente(String rif, String direccion,
                   String telefono_1, String telefono_2,
                   String email, String nombre, String estatus,
                   String s, Date fecha_activacion,
                   Date fecha_actualizacion,
                   Usuario usuarioActivacion,
                   Usuario usuarioActualizacion,
                   Pais pais) {
        this.rif = rif;
        this.direccion = direccion;
        this.telefono_1 = telefono_1;
        this.telefono_2 = telefono_2;
        this.email = email;
        this.nombre = nombre;
        this.estatus = estatus;
        this.fecha_activacion = fecha_activacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.usuarioActivacion = usuarioActivacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.pais = pais;
    }



    public Cliente() {
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public String getRif() {
        return rif;
    }

    public String getDireccion() {
        return direccion;
    }
    @Column(name = "telefono_1")
    public String getTelefono1() {
        return telefono_1;
    }
    @Column(name = "telefono_2")
    public String getTelefono2() {
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
    @Column(name = "fecha_activacion")
    public Date getFechaActivacion() {
        return fecha_activacion;
    }
    @Column(name = "fecha_actualizacion")
    public Date getFechaActualizacion() {
        return fecha_actualizacion;
    }
    @Column(name = "usuarioActivacion")
    public Usuario getUsuarioActivacion() {
        return usuarioActivacion;
    }
    @Column(name = "usuarioActualizacion")
    public Usuario getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setIdcliente(Long idcliente) {
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

    public void setUsuarioActivacion(Usuario usuarioActivacion) {
        this.usuarioActivacion = usuarioActivacion;
    }

    public void setUsuarioActualizacion(Usuario usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
