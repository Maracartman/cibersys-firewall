package com.cibersys.firewall.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "origen")
public class Origen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idorigen")
    private Long idOrigen;

    @Column(columnDefinition = "VARCHAR(100)", name = "nombre")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(255)", name = "descripcion")
    private String descripcion;

    @Column(columnDefinition = "CHAR(1)", name = "mostrar")
    private String mostrar;

    @Column(columnDefinition = "CHAR(1)", name = "estatus")
    private String estatus;
}
