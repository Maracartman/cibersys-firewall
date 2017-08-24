package com.cibersys.firewall.Domain.Model;

import com.cibersys.firewall.Domain.AbstractModel.AbstractSolicitud;
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
@Table(name = "solicitud_vpn")
public class SolicitudVPN extends AbstractSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idsolicitudvpn")
    private Long idsolicitudvpn;


    @Column(columnDefinition = "VARCHAR(255)", name = "usuario")
    private String usuario;

    @Column(columnDefinition = "VARCHAR(62)", name = "contraseña")
    private String contraseña;

    @Column(columnDefinition = "VARCHAR(20)", name = "direccion_ip")
    private String direccionIp;

}
