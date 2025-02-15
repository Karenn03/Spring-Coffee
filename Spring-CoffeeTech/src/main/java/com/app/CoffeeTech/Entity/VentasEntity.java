package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ventas")
public class VentasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVentas")
    private Long idVentas;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    // Relations
    @OneToOne(mappedBy = "ventas", cascade = CascadeType.REMOVE)
    private PedidosEntity pedidos;

    // Breakout Table
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "VentasHasProductos",
            joinColumns = @JoinColumn(name = "idVentas", nullable = false),
            inverseJoinColumns = {
                    @JoinColumn(name = "idProductos", nullable = false),
                    @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto", nullable = false)
            })
    private List<ProductosEntity> productos;

}