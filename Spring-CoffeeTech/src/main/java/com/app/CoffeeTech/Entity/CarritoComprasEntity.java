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
@Table(name = "Carrito_Compras")
public class CarritoComprasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarritoCompras")
    private Integer idCarritoCompras;

    @Column(name = "fecha_agregado", nullable = false, length = 20)
    private LocalDate fechaAgregado;


    @OneToOne
    @JoinColumn(name = "Personas_idPersonas", nullable = false)
    private PersonaEntity persona;

    @OneToMany(mappedBy = "carritoCompras")
    private List<ProductosHasCarritoComprasEntity> productosHasCarritoCompras;

}