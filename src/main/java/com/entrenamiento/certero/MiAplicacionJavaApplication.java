package com.entrenamiento.certero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiAplicacionJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiAplicacionJavaApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // Poblamos la base de datos con usuarios predeterminados
        userRepository.save(new User(null, "Juan Pérez", "juan.perez@correo.com"));
        userRepository.save(new User(null, "Ana Gómez", "ana.gomez@correo.com"));
        userRepository.save(new User(null, "Carlos Rodríguez", "carlos.rodriguez@correo.com"));
    }
}
