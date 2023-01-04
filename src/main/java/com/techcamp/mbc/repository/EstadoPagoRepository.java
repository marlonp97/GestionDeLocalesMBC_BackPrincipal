package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.EstadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los estados de los pagos
 * @author Marlon Pérez Ríos
 */
@Repository
public interface EstadoPagoRepository extends JpaRepository<EstadoPago, Integer> {

    /**
     * Método para buscar un estado de un pago mediante el estado
     * @param estado Estado
     * @return Estado de un pago encontrado
     */
    Optional<EstadoPago> findByEstado(String estado);

}