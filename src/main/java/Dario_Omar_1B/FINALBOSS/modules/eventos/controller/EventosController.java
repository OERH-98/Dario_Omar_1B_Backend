package Dario_Omar_1B.FINALBOSS.modules.eventos.controller;

import Dario_Omar_1B.FINALBOSS.exceptions.BusinessRuleException;
import Dario_Omar_1B.FINALBOSS.exceptions.DataNotFoundException;
import Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto.EventoRequestDTO;
import Dario_Omar_1B.FINALBOSS.modules.eventos.model.dto.EventoResponseDTO;
import Dario_Omar_1B.FINALBOSS.modules.eventos.service.EventoService;
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
@RequestMapping("/api/eventos")
public class EventosController {

    private final EventoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventoResponseDTO>>> obtenerDatos (){
        try{
            List<EventoResponseDTO> lista = service.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los datos con exito", lista));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudieron obtener los datos", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventoResponseDTO>> obtenerDatosPorId(@PathVariable Long id){
        try{
            EventoResponseDTO dto = service.obtenerPorId(id);
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
    public ResponseEntity<ApiResponse<EventoResponseDTO>> nuevoEvento(@Valid @RequestBody EventoRequestDTO json) {
        try {
            EventoResponseDTO dto = service.nuevoEvento(json);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Dato creado con exito", dto));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (BusinessRuleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "Ya existe un evento con ese nombre, cliente y fecha", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudo crear el dato", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventoResponseDTO>> actualizar(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO dto){
        try {
            EventoResponseDTO data = service.actualizarData(dto, id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Dato actualizado con exito", data));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (BusinessRuleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "Ya existe un evento con ese nombre, cliente y fecha", null));
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
                    .body(new ApiResponse<>(false, "No se encontro el evento con id " + id, false));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "No se pudo eliminar el dato", false));
        }
    }
}
