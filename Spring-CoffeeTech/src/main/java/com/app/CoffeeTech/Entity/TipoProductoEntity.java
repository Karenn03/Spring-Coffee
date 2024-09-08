package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tipo_Producto")
public class TipoProductoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoProducto")
    private Integer idTipoProducto;

    @Column(name = "nombre_tipo_prod", nullable = false, length = 100)
    private String nombreTipoProd;

}