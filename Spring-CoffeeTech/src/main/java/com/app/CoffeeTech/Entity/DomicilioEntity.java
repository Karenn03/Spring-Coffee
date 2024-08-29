package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Domicilio")
public class DomicilioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDomicilio")
    private Integer idDomicilio;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;

    @Column(name = "especificaciones", nullable = false, length = 250)
    private String especificaciones;


    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "Pedidos_idPedidos", referencedColumnName = "idPedidos"),
            @JoinColumn(name = "Pedidos_Usuario_idUsuario", referencedColumnName = "Usuario_idUsuario")
    })
    private PedidosEntity pedido;

    // Getters y Setters

}