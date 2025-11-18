package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reseñas")
public class ReviewEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReseñas")
    private Long idReseñas;

    @Column(name = "calificacion", nullable = false)
    private Long calificacion;

    @Column(name = "comentario", nullable = false, length = 200)
    private String comentario;

    @CreationTimestamp
    @Column(name = "fecha_reseña", nullable = false, length = 20)
    private LocalDateTime fechaReseña;

    // Relations
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPersonas", nullable = false)
    private UserEntity persona;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumns({
            @JoinColumn(name = "idProductos", referencedColumnName = "idProductos"),
            @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto")
    })
    private ProductEntity producto;

}