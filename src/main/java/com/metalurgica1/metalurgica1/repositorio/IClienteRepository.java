package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente,Long> {
}
