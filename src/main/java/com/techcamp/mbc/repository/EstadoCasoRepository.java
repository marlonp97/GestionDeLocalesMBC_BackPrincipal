package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.EstadoCaso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los estados de los casos
 * @author Marlon Pérez Ríos
 */
@Repository
public interface EstadoCasoRepository extends JpaRepository<EstadoCaso, Integer> {

    /**
     * Método para buscar un estado de un caso mediante el estado
     * @param estado Estado
     * @return Estado de un caso encontrado
     */
    Optional<EstadoCaso> findByEstado(String estado);

}