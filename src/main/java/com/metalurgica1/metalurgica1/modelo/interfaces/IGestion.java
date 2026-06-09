package com.metalurgica1.metalurgica1.modelo.interfaces;

import com.metalurgica1.metalurgica1.DTO.*;
import com.metalurgica1.metalurgica1.service.Excepciones.ClienteNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.RegistroNoEncontradoException;
import com.metalurgica1.metalurgica1.service.Excepciones.TareaNoEncontradaExeption;

import java.util.List;

public interface IGestion {

    List<ClienteDTO> visualizarClientes();
    List<EmpleadoModeloDTO> visualizarEmpleados();
    List<TareaDTO> visualizarTareas();
    List<RegistroDTO> visualizarRegistros();

    EmpleadoDTO buscarEmpleado(Long legajo);
    TareaDTO buscarTarea(Long id) throws TareaNoEncontradaExeption;
    ClienteDTO buscarCliente(Long id);
    RegistroDTO buscarRegistro(Long id) throws RegistroNoEncontradoException;

    ClienteDTO crearCliente(CrearClienteDTO dto);
    TareaDTO crearTarea(CrearTareaDTO dto);
    RegistroDTO crearRegistro(RegistroDTO dto) throws ClienteNoEncontradoException, TareaNoEncontradaExeption;

    EmpleadoDTO modificarEmpleado(EmpleadoDTO dto, Long legajo);
    ClienteDTO modificarCliente(ClienteDTO dto, Long id) throws ClienteNoEncontradoException;
    RegistroDTO modificarRegistro(RegistroDTO dto, Long id) throws RegistroNoEncontradoException, ClienteNoEncontradoException, TareaNoEncontradaExeption;
    TareaDTO modificarTarea(TareaDTO dto, Long id) throws TareaNoEncontradaExeption;

}
