package com.cibersys.firewall.Domain.AbstractModel;

import com.cibersys.firewall.Domain.Model.Solicitud;
import com.cibersys.firewall.Domain.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Data
@AllArgsConstructor
@MappedSuperclass
public class AbstractSolicitud {

    @Column(columnDefinition = "CHAR(1)", name = "estatus")
    protected String estatus;

    @Column(name = "fecha_ejecucion")
    protected Date fechaEjecucion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idsolicitud")
    protected Solicitud solicitud;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_ejecucion")
    protected Usuario usuarioEjecucion;


    public AbstractSolicitud(){}

}
