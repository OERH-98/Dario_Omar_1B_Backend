package Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventoRequestDTO {

    private Long id_cliente;
    private Long id_salon;
    private String nombre_evento;
    private Date fecha_evento;
    private Long cantidad_personas;
    private Long cantidad_horas;
    private String estado;

}
