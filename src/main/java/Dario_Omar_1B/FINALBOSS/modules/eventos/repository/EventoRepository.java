package Dario_Omar_1B.FINALBOSS.modules.eventos.repository;

import Dario_Omar_1B.FINALBOSS.modules.eventos.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT COUNT(e) > 0 FROM Evento e WHERE e.salon.id_salon = :idSalon AND e.fecha_evento = :fecha AND e.estado IN :estados AND (:idEvento IS NULL OR e.id_evento <> :idEvento)")
    boolean existeEventoActivo(@Param("idSalon") Long idSalon,
                               @Param("fecha") LocalDate fecha,
                               @Param("estados") Collection<String> estados,
                               @Param("idEvento") Long idEvento);
}
