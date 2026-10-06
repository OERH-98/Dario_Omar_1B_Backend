package Dario_Omar_1B.FINALBOSS.modules.salones.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalonResponseDTO {
    private Long id_salon;

    private String nombre_salon;

    private Integer capacidad;

    private BigDecimal precio_renta;

    private String ubicacion;
}
