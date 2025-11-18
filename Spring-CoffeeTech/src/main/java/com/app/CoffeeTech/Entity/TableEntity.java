package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mesas")
public class TableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesas")
    private Long idMesas;

    @Column(name = "Numero", nullable = false)
    private Long numero;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean state;

}