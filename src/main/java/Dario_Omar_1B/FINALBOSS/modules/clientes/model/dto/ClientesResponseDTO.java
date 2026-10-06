package Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto;

import lombok.Data;

@Data
public class ClientesResponseDTO {
    private Long id_cliente;

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private String direccion;
}
