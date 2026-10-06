package Dario_Omar_1B.FINALBOSS.modules.clientes.service;

import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesRequestDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.entity.Cliente;
import Dario_Omar_1B.FINALBOSS.modules.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository repo;

    private Cliente convertirAENTITY(ClientesRequestDTO e){
        Cliente entity = new Cliente();

        entity.setNombre(e.getNombre());
        entity.setApellido(e.getApellido());
        entity.setTelefono(e.getTelefono());
        entity.setEmail(e.getEmail());
        entity.setDireccion(e.getDireccion());

        return entity;
    }

    private ClientesResponseDTO convertertirADTOResponse(Cliente e){
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId_cliente(e.getId_cliente());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setTelefono(e.getTelefono());
        dto.setEmail(e.getEmail());
        dto.setDireccion(e.getDireccion());

        return dto;
    }

}
