package com.metalurgica1.metalurgica1.repositorio;

import com.metalurgica1.metalurgica1.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByDni(int dni);
    Cliente findByEmail(String email);
}
