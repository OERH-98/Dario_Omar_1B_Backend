package Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesRequestDTO {
    private Long id_cliente;

    @NotNull(message = "ERR01: El nombre no puede ir nulo")
    private String nombre;
    @NotNull(message = "ERR01: El apellido no puede ir nulo")
    private String apellido;
    @NotNull(message = "ERR02: El telefono no puede ir nulo")
    private String telefono;
    @NotNull(message = "ERR01: El email no puede ir nulo")
    private String email;
    @NotNull(message = "ERR01: La direccion no puede ir nulo")
    private String direccion;
}
