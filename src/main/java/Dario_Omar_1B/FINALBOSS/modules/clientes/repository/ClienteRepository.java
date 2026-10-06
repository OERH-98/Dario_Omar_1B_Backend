package Dario_Omar_1B.FINALBOSS.modules.clientes.repository;

import Dario_Omar_1B.FINALBOSS.modules.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
