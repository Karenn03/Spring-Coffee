package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Promociones_has_Productos")
public class PromocionesHasProductosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPromociones_has_Productos")
    private Long idPromocionesHasPoductos;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity producto;

    @ManyToOne
    @JoinColumn(name = "idPromociones", nullable = false)
    private PromocionesEntity promocion;

}