package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Carrito_Compras")
public class CarritoComprasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarritoCompras")
    private Integer idCarritoCompras;

    @Column(name = "fecha_agregado", nullable = false, length = 20)
    private LocalDate fechaAgregado;


    @OneToOne(mappedBy = "carritoCompras")
    private PersonaEntity persona;

    @OneToMany(mappedBy = "carritoCompras")
    private List<ProductosHasCarritoComprasEntity> productosHasCarritoCompras;

    // Getters y Setters

    
}