package com.agencia.viajes.service;

import com.agencia.viajes.model.Cliente;
import com.agencia.viajes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteRepository.findById(dni);
    }
    
    public List<Cliente> buscarPorNacionalidad(String nacionalidad) {
        return clienteRepository.findByNacionalidad(nacionalidad);
    }
    
    public List<Cliente> clientesConSeguro() {
        return clienteRepository.findBySeguroViaje("SI");
    }
    
    public Cliente actualizarContacto(String dni, String telefono, String correo) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(dni);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setTelefono(telefono);
            cliente.setCorreoElectronico(correo);
            return clienteRepository.save(cliente);
        }
        return null;
    }
    
    public boolean eliminarCliente(String dni) {
        if (clienteRepository.existsById(dni)) {
            clienteRepository.deleteById(dni);
            return true;
        }
        return false;
    }
}