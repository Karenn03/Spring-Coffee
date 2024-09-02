package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tipo_Producto")
public class TipoProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoProducto")
    private Integer idTipoProducto;

    @Column(name = "nombre_tipo_prod", nullable = false, length = 100)
    private String nombreTipoProd;


    @OneToMany(mappedBy = "tipoProducto")
    private List<ProductosEntity> productos;

}