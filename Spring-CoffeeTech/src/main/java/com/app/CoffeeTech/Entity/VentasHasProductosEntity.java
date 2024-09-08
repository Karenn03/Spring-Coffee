package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ventas_has_Productos")
public class VentasHasProductosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVentas_has_Productos")
    private Long idVentasHasProductos;

    @ManyToOne
    @JoinColumn(name = "idVentas", nullable = false)
    private VentasEntity ventas;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity producto;

}