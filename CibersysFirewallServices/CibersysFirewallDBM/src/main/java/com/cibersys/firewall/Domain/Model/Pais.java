package com.cibersys.firewall.Domain.Model;

import javax.persistence.*;

/**
 * Created by AKDESK25 on 6/14/2017.
 */
@Entity
@Table(name = "pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="INT(11)")
    private Long idpais;

    @Column(columnDefinition="CHAR(1)")
    private String iso;

    private String nombre;

    @Column(columnDefinition="SMALLINT(5)")
    private Integer codigo_telefono;

    public Long getIdpais() {
        return idpais;
    }

    public String getIso() {
        return iso;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCodigo_telefono() {
        return codigo_telefono;
    }

    public void setIdpais(Long idpais) {
        this.idpais = idpais;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo_telefono(Integer codigo_telefono) {
        this.codigo_telefono = codigo_telefono;
    }
}

