package co.com.alianza.fiduciaria.service;

import co.com.alianza.fiduciaria.api.request.ClienteRequest;
import co.com.alianza.fiduciaria.api.response.ClienteResponse;
import co.com.alianza.fiduciaria.exception.ClienteException;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
//entre controlador y tabla
    UUID crearCliente(ClienteRequest request) throws ClienteException;

    List<ClienteResponse> getClientes() throws ClienteException;

    ClienteResponse getClienteBySharedKey(String sharedKey) throws ClienteException;

}
