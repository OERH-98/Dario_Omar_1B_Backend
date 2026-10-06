package Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EventoResponseDTO {
    private Long id_evento;

    private Long id_cliente;

    private String nombre_cliente;

    private Long id_salon;

    private String nombre_salon;

    private String nombre_evento;

    private LocalDate fecha_evento;

    private Integer cantidad_personas;

    private Integer cantidad_horas;

    private String estado;

    private BigDecimal total_pago;
}
