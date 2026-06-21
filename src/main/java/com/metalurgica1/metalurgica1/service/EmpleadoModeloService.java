package com.metalurgica1.metalurgica1.service;

import com.metalurgica1.metalurgica1.DTO.EmpleadoModeloDTO;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.modelo.Empleado_Gerente;
import com.metalurgica1.metalurgica1.modelo.Empleado_modelo;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IGerenteRepository;
import com.metalurgica1.metalurgica1.service.Excepciones.EmpleadoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmpleadoModeloService {

    @Autowired
    private IEmpleadoRepository iEmpleadoRepository;
    @Autowired
    private IGerenteRepository iGerenteRepository;

    public EmpleadoModeloService(IEmpleadoRepository iEmpleadoRepository, IGerenteRepository iGerenteRepository) {
        this.iEmpleadoRepository = iEmpleadoRepository;
        this.iGerenteRepository = iGerenteRepository;
    }

    public List<EmpleadoModeloDTO> listarTodosEmpleados() {
            List<Empleado> empleados = iEmpleadoRepository.findAll();
            List<Empleado_Gerente> gerentes = iGerenteRepository.findAll();
            List<Empleado_modelo> nuevaLista =
                    Stream
                    .concat(empleados.stream(),gerentes.stream())
                    .filter(p->p.getEEstadoActividad() == EEstadoActividad.ACTIVO)
                    .sorted(Comparator.comparing(Empleado_modelo::getLegajo))
                    .toList();

            return nuevaLista.stream().map(p-> new EmpleadoModeloDTO(p.getLegajo(),p.getEmail(),p.getNombre(),p.getTelefono(),p.getDni(),p.getEtiquetaDeAcceso())).toList();
        }

        public EmpleadoModeloDTO buscarEmpleadoPorId(Long legajo){
            return listarTodosEmpleados().stream().filter(p->p.legajo().equals(legajo)).findFirst().orElseThrow(()->new RuntimeException("Empleado con id"+ legajo+" no encontrado"));
        }

        public Optional<EmpleadoModeloDTO> buscarEmpleadoPorEmail(String email){
            return listarTodosEmpleados().stream().filter(p->p.email().equals(email)).findFirst();
        }

        public Optional<EmpleadoModeloDTO> buscarEmpleadoPorNombre(String nombre){
            return listarTodosEmpleados().stream().filter(p->p.nombre().equals(nombre)).findFirst();
        }

        public Optional<EmpleadoModeloDTO> buscarEmpleadoPorTelefono(String telefono){
            return listarTodosEmpleados().stream().filter(p->p.telefono().equals(telefono)).findFirst();
        }

        public Optional<EmpleadoModeloDTO> buscarEmpleadoPorDni(Long dni){
            return listarTodosEmpleados().stream().filter(p->p.dni().equals(dni)).findFirst();
        }

}
