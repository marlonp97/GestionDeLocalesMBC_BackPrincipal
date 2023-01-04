package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.EmpresaPagoDTO;
import com.techcamp.mbc.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de las empresas
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    /**
     * Método para convertir modelo de la empresa a DTO
     * @param empresa Empresa
     * @return DTO de la empresa
     */
    @Mappings({
            @Mapping(source = "idEmpresa", target = "idEmpresa"),
            @Mapping(source = "nombre", target = "nombre")
    })
    EmpresaPagoDTO empresaToEmpresaPagoDTO(Empresa empresa);
    /**
     * Método para convertir lista de modelos de empresas a lista DTO
     * @param empresa Lista de empresas
     * @return Lista DTO de las empresas
     */
    List<EmpresaPagoDTO> empresaToEmpresaPagoDTOList(List<Empresa> empresa);

}