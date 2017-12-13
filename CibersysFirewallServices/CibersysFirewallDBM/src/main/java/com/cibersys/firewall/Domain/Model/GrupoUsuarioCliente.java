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
@Table(name = "grupo_usuario_cliente")
public class GrupoUsuarioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", name = "idgrupousuario")
    private Long idgrupousuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuariocliente")
    private UsuarioCliente usuarioCliente;


    @OneToOne
    @JoinColumn(name = "idgrupo")
    private Grupo grupo;

    @Column(columnDefinition = "CHAR(1)",name = "estatus")
    private String estatus;
}
