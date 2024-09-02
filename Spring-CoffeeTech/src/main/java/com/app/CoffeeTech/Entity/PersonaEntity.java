package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Personas")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonas")
    private Integer idPersonas;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "nombre_usuario", nullable = false, length = 50, unique = true)
    private String nombreUsuario;

    @Column(name = "contraseña", nullable = false, length = 50)
    private String contraseña;

    @Column(name = "p_nombre", nullable = false, length = 25)
    private String primerNombre;

    @Column(name = "s_nombre", length = 25)
    private String segundoNombre;

    @Column(name = "p_apellido", nullable = false, length = 25)
    private String primerApellido;

    @Column(name = "s_apellido", length = 25)
    private String segundoApellido;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;


    @OneToMany(mappedBy = "persona")
    private List<ReservaEntity> reserva;

    @OneToMany(mappedBy = "persona")
    private List<RolesHasPersonasEntity> rolesHasPersonas;

    @OneToOne(mappedBy = "persona")
    private CarritoComprasEntity carrito;

    @OneToMany(mappedBy = "persona")
    private PedidosEntity pedidos;

    @OneToMany(mappedBy = "persona")
    private ReseñasEntity reseñas;

    // Getters and Setters

}