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
@Table(name = "estatus")
public class Estatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "SMALLINT(2)", name = "idestatus")
    private Long idEstatus;

    @Column(columnDefinition = "VARCHAR(20)",name = "nombre")
    private String nombre;

    @Column(columnDefinition = "CHAR(1)",name = "es_inicial")
    private String esInicial;

    @Column(columnDefinition = "CHAR(1)",name = "es_final")
    private String esFinal;

    @Column(columnDefinition = "CHAR(1)",name = "estatus")
    private String estatus;
}
