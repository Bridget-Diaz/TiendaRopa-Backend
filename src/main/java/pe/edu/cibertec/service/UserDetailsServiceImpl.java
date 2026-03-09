package pe.edu.cibertec.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.*;

import pe.edu.cibertec.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado"));

        String rol = "ROLE_" + usuario.getRol().getNombreRol().toUpperCase();

        return new User(
            usuario.getEmail(),
            usuario.getPassword(),
            !usuario.getBloqueado(), // 👈 AQUÍ ESTÁ LA CLAVE
            true,   // accountNonExpired
            true,   // credentialsNonExpired
            true,   // accountNonLocked
            Collections.singletonList(new SimpleGrantedAuthority(rol))
        );
    }

}
