package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reseñas")
public class ReseñasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReseñas")
    private Long idReseñas;

    @Column(name = "calificacion", nullable = false)
    private Long calificacion;

    @Column(name = "comentario", nullable = false, length = 200)
    private String comentario;

    @Column(name = "fecha_reseña", nullable = false, length = 20)
    private LocalDateTime fechaReseña;

    @ManyToOne
    @JoinColumn(name = "idPersonas", nullable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idProductos", referencedColumnName = "idProductos"),
            @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto")
    })
    private ProductosEntity producto;

}