package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA de los pagos
 * @author Marlon Pérez Ríos
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    /**
     * Método para buscar los pagos finalizados de una empresa
     * @param idEmpresa ID de la empresa
     * @return Pagos finalizados encontrados
     */
    @Query("SELECT p FROM Pago p WHERE p.estadoPago.estado = 'Pagado' and p.local.empresa.idEmpresa = :idEmpresa")
    List<Pago> findHistoricoByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    /**
     * Método para buscar los pagos pendientes de una empresa
     * @param idEmpresa ID de la empresa
     * @return Pagos pendientes encontrados
     */
    @Query("SELECT p FROM Pago p WHERE p.estadoPago.estado != 'Pagado' and p.local.empresa.idEmpresa = :idEmpresa")
    List<Pago> findPendientesByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

}