package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los usuarios
 * @author Marlon Pérez Ríos
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método para buscar un usuario mediante el correo
     * @param correo Correo del usuario
     * @return Usuario encontrado
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Método para buscar un usuario mediante el correo y la clave
     * @param correo Correo del usuario
     * @param clave Clave del usuario
     * @return Usuario encontrado
     */
    Optional<Usuario> findByCorreoAndClave(String correo, String clave);

}