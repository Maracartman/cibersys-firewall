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
@Table(name = "solicitud_site")
public class SolicitudSiteToSite extends AbstractSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idsolicitudsite")
    private Long idSolicitudSite;

    @Column(columnDefinition = "VARCHAR(73)", name = "sucursal")
    private String sucursal;


    @Column(columnDefinition = "VARCHAR(255)", name = "comentario")
    private String comentario;




}
