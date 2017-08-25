package com.cibersys.firewall.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.security.acl.Group;
import java.util.Date;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Entity
@AllArgsConstructor
@Data
@Table(name = "usuario_cliente")
public class UsuarioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)",name = "idusuariocliente")
    private Long idUsuarioCliente;

    @Column(columnDefinition = "VARCHAR(100)",name = "nombre")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(100)",name = "apellido")
    private String apellido;

    @Column(columnDefinition = "VARCHAR(255)",name = "email")
    private String email;

    @Column(columnDefinition = "VARCHAR(20)",name = "direccion_ip")
    private String direccionIp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_creacion")
    private Usuario usuarioCreacion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(columnDefinition = "CHAR(1)",name = "estatus")
    private String estatus;

    public UsuarioCliente() {
    }
}
