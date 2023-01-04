package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.Caso;
import com.techcamp.mbc.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de los casos
 * @author Marlon Pérez Ríos
 */
@Repository
public interface CasoRepository extends JpaRepository<Caso, Long> {

    /**
     * Método para buscar un caso mediante un pago
     * @param pago Pago
     * @return Caso encontrado
     */
    Optional<Caso> findByPago(Pago pago);

}