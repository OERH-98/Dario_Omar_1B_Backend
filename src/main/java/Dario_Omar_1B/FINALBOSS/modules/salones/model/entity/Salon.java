package Dario_Omar_1B.FINALBOSS.modules.salones.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "salones")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salon")
    private Long id_salon;
    @Column(name = "nombre_salon", unique = true, nullable = false)
    private String nombre_salon;
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    @Column(name = "precio_renta", nullable = false)
    private BigDecimal precio_renta;
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;
}
