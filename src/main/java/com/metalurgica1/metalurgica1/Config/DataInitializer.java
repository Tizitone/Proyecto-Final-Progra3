package com.metalurgica1.metalurgica1.Config;

import com.metalurgica1.metalurgica1.modelo.Administrador;
import com.metalurgica1.metalurgica1.modelo.enums.EEstadoActividad;
import com.metalurgica1.metalurgica1.repositorio.IAdministradorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final IAdministradorRepository iAdministradorRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(IAdministradorRepository iAdministradorRepository, PasswordEncoder passwordEncoder) {
        this.iAdministradorRepository = iAdministradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String emailAdmin = "admin@metalurgica.com";

        if (iAdministradorRepository.findByEmail(emailAdmin) == null) {

            Administrador admin = new Administrador();
            admin.setNombre("Administrador Global");
            admin.setEmail(emailAdmin);
            admin.setDni(99999999L);
            admin.setTelefono("00000000");
            admin.setEEstadoActividad(EEstadoActividad.ACTIVO);

            String passwordEncriptada = passwordEncoder.encode("Admin1234");
            admin.setContrasenia(passwordEncriptada);

            iAdministradorRepository.save(admin);

            System.out.println("Cuenta de Administrador inicial hardcodeada con éxito.");
        }
    }
}
