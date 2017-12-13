package com.cibersys.firewall.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idsolicitud")
    private Long idSolicitud;

    @Column(columnDefinition = "VARCHAR(20)", name = "numero")
    private String numero;

    @Column(columnDefinition = "CHAR(1)", name = "categoria")
    private String categoria;

    @Column(columnDefinition = "VARCHAR(255)", name = "descripcion")
    private String descripcion;


    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    /**
     * Campos relacionales
     ***/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcliente")
    private Cliente cliente;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_creacion")
    private Usuario usuarioCreacion;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_asignado")
    private Usuario usuarioAsignado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idestatus")
    private Estatus estatus;
}
