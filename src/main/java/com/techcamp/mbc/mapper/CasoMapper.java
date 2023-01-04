package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.CasoDTO;
import com.techcamp.mbc.model.Caso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los casos
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface CasoMapper {

    /**
     * Método para convertir modelo del caso a DTO
     * @param caso Caso
     * @return DTO del caso
     */
    @Mappings({
            @Mapping(source = "idCaso", target = "idCaso"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "soporte", target = "soporte"),
            @Mapping(source = "estadoCaso.estado", target = "estadoCaso"),
            @Mapping(source = "usuario.correo", target = "usuario"),
            @Mapping(source = "pago.idPago", target = "pagoCaso.idPago"),
            @Mapping(source = "pago.descripcion", target = "pagoCaso.descripcion"),
            @Mapping(source = "pago.empresa.nombre", target = "empresa"),
            @Mapping(source = "pago.local.direccion", target = "local")
    })
    CasoDTO casoToCasoDTO(Caso caso);
    /**
     * Método para convertir lista de modelos de casos a lista DTO
     * @param caso Lista de casos
     * @return Lista DTO de los casos
     */
    List<CasoDTO> casoToCasoDTOList(List<Caso> caso);

}