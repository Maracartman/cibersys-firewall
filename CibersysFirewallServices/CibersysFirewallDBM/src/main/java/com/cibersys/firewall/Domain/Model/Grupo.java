package com.cibersys.firewall.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by AKDESK25 on 8/24/2017.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)",name = "idgrupo")
    private Long idGrupo;

    @Column(columnDefinition = "VARCHAR(100)",name = "nombre")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(255)",name = "descripcion")
    private String descripcion;

    @Column(columnDefinition = "VARCHAR(20)",name = "direccion_ip")
    private String direccionIp;

    @Column(columnDefinition = "CHAR(1)",name = "tipo_ip")
    private String tipoIp;

    @Column(columnDefinition = "CHAR(1)",name = "estatus")
    private String estatus;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @OneToOne
    @JoinColumn(name = "usuario_creacion")
    private Usuario usuarioCreacion;

    public Grupo() {
    }
    /**TODO: PARA TESTEAR EL FUNCIONAMIENTO
     * **/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "grupo_usuario_cliente",
            joinColumns = @JoinColumn(name = "idgrupo",referencedColumnName = "idgrupo"),
    inverseJoinColumns = @JoinColumn(name = "idusuariocliente",referencedColumnName = "idusuariocliente"))
    private List<UsuarioCliente> userClients;


}
