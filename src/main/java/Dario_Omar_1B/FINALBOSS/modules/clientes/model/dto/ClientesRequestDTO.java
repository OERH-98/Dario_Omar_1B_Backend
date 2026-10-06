package Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientesRequestDTO {

    @NotBlank(message = "ERR01: El nombre no puede ir vacio")
    @Size(max = 100, message = "ERR03: El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "ERR01: El apellido no puede ir vacio")
    @Size(max = 100, message = "ERR03: El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "ERR01: El telefono no puede ir vacio")
    @Pattern(regexp = "^[0-9+() -]{7,15}$", message = "ERR02: El telefono debe tener entre 7 y 15 caracteres validos (numeros, +, (, ), -)")
    private String telefono;

    @NotBlank(message = "ERR01: El email no puede ir vacio")
    @Size(max = 100, message = "ERR03: El email no puede exceder 100 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "ERR02: El email no tiene un formato valido")
    private String email;

    @NotBlank(message = "ERR01: La direccion no puede ir vacia")
    @Size(max = 200, message = "ERR03: La direccion no puede exceder 200 caracteres")
    private String direccion;
}
