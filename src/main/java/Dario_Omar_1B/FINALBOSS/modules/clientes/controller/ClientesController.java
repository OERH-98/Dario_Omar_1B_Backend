package Dario_Omar_1B.FINALBOSS.modules.clientes.controller;

import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesRequestDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.model.dto.ClientesResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.clientes.service.ClienteService;
import Dario_Omar_1B.FINALBOSS.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClienteService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesResponseDTO>>> obtenerDatos (){
        try{
            List<ClientesResponseDTO> lista = service.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los datos con exito", lista));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudieron obtener los datos", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesResponseDTO>> obtenerDatosPorId(@PathVariable Long id){
        try{
            ClientesResponseDTO dto = service.obtenerPorId(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los datos con exito", dto));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudieron obtener los datos", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientesResponseDTO>> nuevoCliente(@Valid @RequestBody ClientesRequestDTO json) {
        try {
            ClientesResponseDTO dto = service.nuevoCliente(json);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Dato creado con exito", dto));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "Ya existe un cliente con ese email", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudo crear el dato", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesResponseDTO>> actualizar(@PathVariable Long id, @Valid @RequestBody ClientesRequestDTO dto){
        try {
            ClientesResponseDTO data = service.actualizarData(dto, id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Dato actualizado con exito", data));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "Ya existe un cliente con ese email", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudo actualizar el dato", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> eliminarDatos(@PathVariable Long id){
        try {
            if (service.eliminar(id)) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Dato eliminado con exito", true));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No se encontro el cliente con id " + id, false));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "No se puede eliminar un cliente que tiene eventos asociados", false));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudo eliminar el dato", false));
        }
    }
}
