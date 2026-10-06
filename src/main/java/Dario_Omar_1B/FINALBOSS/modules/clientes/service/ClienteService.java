package Dario_Omar_1B.FINALBOSS.modules.clientes.service;

import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesRequestDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.entity.Cliente;
import Dario_Omar_1B.FINALBOSS.modules.clientes.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private ClientesResponseDTO convertirADTOResponse(Cliente e){
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId_cliente(e.getId_cliente());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setTelefono(e.getTelefono());
        dto.setEmail(e.getEmail());
        dto.setDireccion(e.getDireccion());

        return dto;
    }

    public List<ClientesResponseDTO> obtenerTodos(){
        List<Cliente> entidades = repo.findAll();

        List<ClientesResponseDTO> dtos = new ArrayList<>();

        for(Cliente entity : entidades){
            dtos.add(convertirADTOResponse(entity));
        }

        return dtos;
    }

    public ClientesResponseDTO obtenerPorId (Long id){
        Optional<Cliente> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()){
            return convertirADTOResponse(entidadOpcional.get());
        }
        throw new DataNotFoundException("Error No se pudo obtener por ID");
    }

    public boolean eliminar (Long id){
        if (repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public ClientesResponseDTO nuevoCliente (@Valid ClientesRequestDTO dto, Long id){
        Cliente datosConvertidos = convertirAENTITY(dto);

        Cliente respuesta = repo.save(datosConvertidos);

        return convertirADTOResponse(respuesta);
    }

}
