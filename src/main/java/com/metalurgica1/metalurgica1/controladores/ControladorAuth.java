package com.metalurgica1.metalurgica1.controladores;

import com.metalurgica1.metalurgica1.Config.JwtUtil;
import com.metalurgica1.metalurgica1.DTO.AuthRequest;
import com.metalurgica1.metalurgica1.DTO.AuthResponse;
import com.metalurgica1.metalurgica1.modelo.Administrador;
import com.metalurgica1.metalurgica1.modelo.Cliente;
import com.metalurgica1.metalurgica1.modelo.Empleado_modelo;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ControladorAuth {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final IAdministradorRepository administradorRepository;
    private final IClienteRepository clienteRepository;
    private final IEmpleadoRepository empleadoRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.contrasenia())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String jwt = jwtUtil.generateToken(userDetails);

        Long idUsuario = null;

        Administrador admin = administradorRepository.findByEmail(request.email());
        if (admin != null) idUsuario = admin.getId_administrador();

        Cliente cliente = clienteRepository.findByEmail(request.email());
        if (cliente != null) idUsuario = cliente.getIdCliente();

        Empleado_modelo empleado = empleadoRepository.findByEmail(request.email());
        if (empleado != null) idUsuario = empleado.getLegajo();

        String rol = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .findFirst()
                .orElse("USER")
                .replace("ROLE_", "");

        return ResponseEntity.ok(new AuthResponse(jwt, rol, idUsuario));
    }
}