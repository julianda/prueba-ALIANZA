package co.com.alianza.fiduciaria.service.impl;

import co.com.alianza.fiduciaria.api.request.ClienteRequest;
import co.com.alianza.fiduciaria.api.response.ClienteResponse;
import co.com.alianza.fiduciaria.exception.ClienteException;
import co.com.alianza.fiduciaria.model.Cliente;
import co.com.alianza.fiduciaria.respository.ClienteRepository;
import co.com.alianza.fiduciaria.service.ClienteService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UUID crearCliente(ClienteRequest request) {
        if (!clienteRepository.findClienteByNombre(request.getNombre()).isPresent()) {

            log.info("Creando cliente {}", request.getNombre());

            String[] nombreCompleto = request.getNombre().split(" ");
            Cliente nuevoCliente = Cliente.builder()
                    .nombre(nombreCompleto[0])
                    .apellido(nombreCompleto[1])
                    .sharedKey(nombreCompleto[0].charAt(0) + nombreCompleto[1])
                    .idNegocio(request.getNombre())
                    .telefono(request.getTelefono())
                    .email(request.getEmail())
                    .fechaIni(request.getFechaIni())
                    .fechaFin(request.getFechaFin())
                    .build();
            clienteRepository.save(nuevoCliente);

            return nuevoCliente.getId();
        } else {
            throw new ClienteException(String.format("El cliente %s ya existe.", request.getNombre()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ClienteResponse> getClientes() {
        List<Cliente> clienteList = clienteRepository.findAll();
        if(!clienteList.isEmpty()){
            return clienteList.stream().map(cliente -> {
                return ClienteResponse.builder()
                        .sharedKey(cliente.getSharedKey())
                        .idNegocio(cliente.getIdNegocio())
                        .telefono(cliente.getTelefono())
                        .email(cliente.getEmail())
                        .fechaIni(cliente.getFechaIni())
                        .build();
            }).collect(toList());
        } else {
            throw new ClienteException("No fue encontrado ningún cliente", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ClienteResponse getClienteBySharedKey(String sharedKey) {

        Optional<Cliente> cliente = clienteRepository.findClienteBySharedKey(sharedKey);
        if(cliente.isPresent()){
            return ClienteResponse.builder()
                    .sharedKey(cliente.get().getSharedKey())
                    .idNegocio(cliente.get().getIdNegocio())
                    .telefono(cliente.get().getIdNegocio())
                    .email(cliente.get().getEmail())
                    .fechaIni(cliente.get().getFechaIni())
                    .build();
        } else {
            throw new ClienteException("No se encontró un cliente con el shared key: "
                    .concat(sharedKey), HttpStatus.NOT_FOUND);
        }
    }
}
