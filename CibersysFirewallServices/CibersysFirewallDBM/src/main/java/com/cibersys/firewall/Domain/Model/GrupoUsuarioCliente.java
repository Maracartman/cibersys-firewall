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
@IdClass(GrupoUsuarioClienteKey.class)
public class GrupoUsuarioCliente {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuariocliente")
    private UsuarioCliente usuarioCliente;

    @Id
    @OneToOne
    @JoinColumn(name = "idgrupo")
    private Grupo grupo;

    @Column(columnDefinition = "CHAR(1)",name = "estatus")
    private String estatus;
}
