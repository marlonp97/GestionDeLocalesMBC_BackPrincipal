package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los estados de los casos
 * @author Marlon Pérez Ríos
 */
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {

    /**
     * Método para buscar un tipo de un pago mediante el tipo
     * @param tipo Tipo
     * @return Tipo de un pago encontrado
     */
    Optional<TipoPago> findByTipo(String tipo);

}