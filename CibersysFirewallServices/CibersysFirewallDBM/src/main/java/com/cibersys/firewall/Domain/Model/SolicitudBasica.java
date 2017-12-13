package com.cibersys.firewall.Domain.Model;

import com.cibersys.firewall.Domain.AbstractModel.AbstractSolicitud;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "solicitud_basica")
public class SolicitudBasica extends AbstractSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idsolicitudbasica")
    private Long idSolicitudBasica;

    @Column(columnDefinition = "VARCHAR(100)", name = "direccion_origen")
    private String direccionOrigen;

    @Column(columnDefinition = "VARCHAR(10)", name = "puerto_origen")
    private String puertoOrigen;

    @Column(columnDefinition = "VARCHAR(100)", name = "direccion_destino")
    private String direccionDestino;

    @Column(columnDefinition = "VARCHAR(10)", name = "puerto_destino")
    private String puertoDestino;

    @Column(columnDefinition = "VARCHAR(240)", name = "comentario")
    private String comentario;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idaccion")
    private Accion accion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idprotocolo")
    private Protocolo protocolo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idorigen")
    private Origen origen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddestino")
    private Destino destino;
}
