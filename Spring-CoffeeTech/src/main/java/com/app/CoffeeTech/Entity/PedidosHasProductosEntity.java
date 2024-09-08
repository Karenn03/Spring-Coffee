package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pedidos_has_Productos")
public class PedidosHasProductosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidos_has_Productos", nullable = false)
    private Long idPedidosHasProductos;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idPedidos", referencedColumnName = "idPedidos", insertable = false, updatable = false),
            @JoinColumn(name = "idMesas", referencedColumnName = "idMesas", insertable = false, updatable = false),
            @JoinColumn(name = "idVentas", referencedColumnName = "idVentas", insertable = false, updatable = false),
            @JoinColumn(name = "idPersonas", referencedColumnName = "idPersonas", insertable = false, updatable = false)
    })
    private PedidosEntity pedidos;

    @ManyToOne
    @JoinColumn(name = "idProductos")
    private ProductosEntity productos;

}