package com.techcamp.mbc.repository;

import com.techcamp.mbc.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA de las empresas
 * @author Marlon Pérez Ríos
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    /**
     * Método para buscar una empresa mediante el nombre
     * @param nombre Nombre de la empresa
     * @return Empresa encontrada
     */
    Optional<Empresa> findByNombre(String nombre);

}