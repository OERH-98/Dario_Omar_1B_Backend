package Dario_Omar_1B.FINALBOSS.modules.eventos.model.entity;

import Dario_Omar_1B.FINALBOSS.modules.clientes.model.entity.Cliente;
import Dario_Omar_1B.FINALBOSS.modules.salones.model.entity.Salon;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id_evento;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_salon", nullable = false)
    private Salon salon;
    @Column(name = "nombre_evento", nullable = false)
    private String nombre_evento;
    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fecha_evento;
    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidad_personas;
    @Column(name = "cantidad_horas", nullable = false)
    private Integer cantidad_horas;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "total_pago", nullable = false)
    private BigDecimal total_pago;
}
