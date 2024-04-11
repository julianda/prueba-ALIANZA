package co.com.alianza.fiduciaria.api.rest;

import co.com.alianza.fiduciaria.api.request.ClienteRequest;
import co.com.alianza.fiduciaria.api.response.ClienteResponse;
import co.com.alianza.fiduciaria.exception.ClienteException;
import co.com.alianza.fiduciaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping("/api")
@Tag(name = "Cliente controller", description = "Acciones cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Crear un cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "cliente creado."),
                    @ApiResponse(responseCode = "400", description = "Error en los datos ingresados.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error.", content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UUID> crearCliente( @RequestBody ClienteRequest clienteRequest)
            throws ClienteException {
        return new ResponseEntity<>(clienteService.crearCliente(clienteRequest), HttpStatus.CREATED);
    }


    @Operation(summary = "Todos los clientes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Clientes."),
                    @ApiResponse(responseCode = "400", description = "Error en los datos ingresados.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error.", content = @Content
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<ClienteResponse>> getAllClientes()
            throws ClienteException {
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);
    }

    @Operation(summary = "Consultar un cliente por Shared Key")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente."),
                    @ApiResponse(responseCode = "400", description = "Error en los datos ingresados.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error.", content = @Content
                    )
            }
    )
    @GetMapping("/{sharedKey}")
    public ResponseEntity<Void> clienteBySharedKey(@NotNull @PathVariable String sharedKey) throws ClienteException {
        clienteService.getClienteBySharedKey(sharedKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
