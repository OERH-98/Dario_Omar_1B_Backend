package Dario_Omar_1B.FINALBOSS.modules.salones.repository;

import Dario_Omar_1B.FINALBOSS.modules.salones.model.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
}
