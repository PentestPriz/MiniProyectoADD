package com.agencia.viajes.repository;

import com.agencia.viajes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNacionalidad(String nacionalidad);
    List<Cliente> findBySeguroViaje(String seguroViaje);
}
