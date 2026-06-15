package com.metalurgica1.metalurgica1.Config;

import com.metalurgica1.metalurgica1.modelo.Persona;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import com.metalurgica1.metalurgica1.repositorio.IClienteRepository;
import com.metalurgica1.metalurgica1.repositorio.IEmpleadoRepository;
import com.metalurgica1.metalurgica1.repositorio.IGerenteRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IAdministradorRepository iAdministradorRepository;
    private final IClienteRepository iClienteRepository;
    private final IEmpleadoRepository iEmpleadoRepository;
    private final IGerenteRepository iGerenteRepository;

    public CustomUserDetailsService(IAdministradorRepository iAdministradorRepository, IClienteRepository iClienteRepository, IEmpleadoRepository iEmpleadoRepository, IGerenteRepository iGerenteRepository) {
        this.iAdministradorRepository = iAdministradorRepository;
        this.iClienteRepository = iClienteRepository;
        this.iEmpleadoRepository = iEmpleadoRepository;
        this.iGerenteRepository = iGerenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Try Admin
        Persona user = iAdministradorRepository.findByEmailOptional(email).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getContrasenia())
                    .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    .build();
        }

        // Try Cliente
        user = iClienteRepository.findByEmailOptional(email).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getContrasenia())
                    .authorities(new SimpleGrantedAuthority("ROLE_CLIENTE"))
                    .build();
        }

        // Try Empleado
        user = iEmpleadoRepository.findByEmailOptional(email).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getContrasenia())
                    .authorities(new SimpleGrantedAuthority("ROLE_EMPLEADO"))
                    .build();
        }

        // Try Gerente
        user = iGerenteRepository.findByEmailOptional(email).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getContrasenia())
                    .authorities(new SimpleGrantedAuthority("ROLE_GERENTE"))
                    .build();
        }

        throw new UsernameNotFoundException("Usuario no encontrado con mail: " + email);
    }
}
