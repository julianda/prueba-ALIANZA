package co.com.alianza.fiduciaria.respository;

import co.com.alianza.fiduciaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findClienteByNombre(String nombre);

    Optional<Cliente> findClienteBySharedKey(String sharedKey);

}
