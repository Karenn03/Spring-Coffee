package Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Mesas")
public class MesasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesas")
    private Integer idMesas;

    @Column(name = "numero_mesa", nullable = false)
    private Integer numeroMesa;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;


    @OneToMany(mappedBy = "mesas")
    private List<PedidosEntity> pedidos;

    // Getters and Setters

}