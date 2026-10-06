package Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequestDTO {

    @NotNull(message = "ERR01: El id del cliente no puede ir nulo")
    @Positive(message = "ERR02: El id del cliente debe ser mayor a 0")
    private Long id_cliente;

    @NotNull(message = "ERR01: El id del salon no puede ir nulo")
    @Positive(message = "ERR02: El id del salon debe ser mayor a 0")
    private Long id_salon;

    @NotBlank(message = "ERR01: El nombre del evento no puede ir vacio")
    @Size(max = 100, message = "ERR03: El nombre del evento no puede exceder 100 caracteres")
    private String nombre_evento;

    @NotNull(message = "ERR01: La fecha del evento no puede ir nula")
    private LocalDate fecha_evento;

    @NotNull(message = "ERR01: La cantidad de personas no puede ir nula")
    @Positive(message = "ERR02: La cantidad de personas debe ser mayor a 0")
    private Integer cantidad_personas;

    @NotNull(message = "ERR01: La cantidad de horas no puede ir nula")
    @Min(value = 1, message = "ERR02: La cantidad de horas debe ser como minimo 1")
    @Max(value = 24, message = "ERR02: La cantidad de horas no puede exceder 24")
    private Integer cantidad_horas;

    @Pattern(regexp = "^(PENDIENTE|CONFIRMADO|CANCELADO|FINALIZADO)$", message = "ERR02: El estado debe ser PENDIENTE, CONFIRMADO, CANCELADO o FINALIZADO")
    private String estado;
}
