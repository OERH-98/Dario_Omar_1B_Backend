package Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ClientesRequestDTO {
    private Long id_cliente;

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private String direccion;
}
