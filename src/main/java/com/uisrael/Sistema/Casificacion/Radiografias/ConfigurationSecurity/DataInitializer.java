package com.uisrael.Sistema.Casificacion.Radiografias.ConfigurationSecurity;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.UsuarioEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Repository.IUsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner crearUsuarioAdmin(IUsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByUsername("admin") == null) {
                UsuarioEntity admin = new UsuarioEntity();
                admin.setUsername("admin");
                admin.setPassword("$2a$12$cV.G6MddyyPsM91RUiRDH.tSZJoSzhKrPv1EZ9ppUMtC8VFih1oCO"); // contraseña: admin
                admin.setRol("ADMIN");
                admin.setEnabled(true);
                usuarioRepository.save(admin);
                System.out.println("✅ Usuario admin creado por defecto");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe");
            }
        };
    }
}
