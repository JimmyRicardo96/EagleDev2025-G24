package com.uisrael.Sistema.Casificacion.Radiografias.Services.Impl;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.UsuarioEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Repository.IUsuarioRepository;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioServicio {
    @Autowired
    private IUsuarioRepository repositorioUsuario;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario) {
        if (usuario.getId() == null || !usuario.getPassword().isEmpty()) {
            // Encriptar la contraseña solo si es nueva o fue modificada
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            // Si está en blanco (en edición), cargar la contraseña anterior desde DB
            UsuarioEntity existente = repositorioUsuario.findById(usuario.getId()).orElseThrow();
            usuario.setPassword(existente.getPassword());
        }

        return repositorioUsuario.save(usuario);
    }

    @Override
    public Optional<UsuarioEntity> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<UsuarioEntity> findAll() {
        try {
            return repositorioUsuario.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public UsuarioEntity findById(int id) {
        try {
            return repositorioUsuario.findById(id).orElseThrow();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            repositorioUsuario.findById(id).orElseThrow();
        } catch (Exception e) {
            System.out.println("Usuario no encontrado" + e.getMessage());
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = repositorioUsuario.findByUsername(username);
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }
}
