package com.cibersys.firewall.Domain.Model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Luis Maracara on 6/14/2017.
 */
@Entity
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_activacion")
    private Usuario usuarioActivacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_actualizacion")
    private Usuario usuarioActualizacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpais")
    private Pais pais;

    public Cliente() {
    }

    public Integer getIdcliente() {
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
}
