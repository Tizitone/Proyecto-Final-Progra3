package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Empleado;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:8080")
public class ControladorCliente {

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public class MyCorsConfig implements Filter {

        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            final HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, enctype");
            response.setHeader("Access-Control-Max-Age", "3600");
            if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, res);
            }
        }

        @Override
        public void destroy() {
        }

        @Override
        public void init(FilterConfig config) throws ServletException {
        }
    }

    private final IClienteRepository iClienteRepository;

    public ControladorCliente(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    @GetMapping
    public List<Cliente> listarClientes()
    {
        return iClienteRepository.findAll();
    }
    @PostMapping
    public String crearCliente(@RequestBody Cliente cliente)
    {
        iClienteRepository.save(cliente);
        return "Cliente creado con exito";
    }
    @PutMapping("/{id}")
    public String modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente)
    {
        cliente.setIdCliente(id);
        iClienteRepository.save(cliente);
        return "Cliente modificado con exito";
    }
}
