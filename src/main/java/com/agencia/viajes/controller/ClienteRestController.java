package com.agencia.viajes.controller;

import com.agencia.viajes.model.Cliente;
import com.agencia.viajes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    
    @Autowired
    private ClienteService clienteService;
    
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }
    
    @GetMapping("/{dni}")
    public ResponseEntity<Cliente> buscarPorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<Cliente>> buscarPorNacionalidad(@PathVariable String nacionalidad) {
        return ResponseEntity.ok(clienteService.buscarPorNacionalidad(nacionalidad));
    }
    
    @GetMapping("/con-seguro")
    public ResponseEntity<List<Cliente>> clientesConSeguro() {
        return ResponseEntity.ok(clienteService.clientesConSeguro());
    }
    
    @PutMapping("/{dni}/contacto")
    public ResponseEntity<Cliente> actualizarContacto(
            @PathVariable String dni,
            @RequestBody Map<String, String> contacto) {
        Cliente actualizado = clienteService.actualizarContacto(
                dni, 
                contacto.get("telefono"), 
                contacto.get("correoElectronico")
        );
        return actualizado != null 
                ? ResponseEntity.ok(actualizado) 
                : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String dni) {
        return clienteService.eliminarCliente(dni)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}