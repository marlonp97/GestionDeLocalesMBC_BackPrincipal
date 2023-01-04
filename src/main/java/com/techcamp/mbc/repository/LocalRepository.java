package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los locales
 * @author Marlon Pérez Ríos
 */
@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    /**
     * Método para buscar un local mediante la dirección
     * @param direccion Dirección del local
     * @return Local encontrado
     */
    Optional<Local> findByDireccion(String direccion);

}