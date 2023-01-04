package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.LocalDTO;
import com.techcamp.mbc.dto.LocalPagoDTO;
import com.techcamp.mbc.model.Local;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los locales
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface LocalMapper {

    /**
     * Método para convertir modelo del local a DTO
     * @param local Local
     * @return DTO del local
     */
    @Mappings({
            @Mapping(source = "idLocal", target = "idLocal"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "cuotaArriendo", target = "cuotaArriendo"),
            @Mapping(source = "cuotaAdministracion", target = "cuotaAdministracion"),
            @Mapping(source = "ciudadLocal.ciudad", target = "ciudadLocal"),
            @Mapping(source = "empresa.nombre", target = "empresa")
    })
    LocalDTO localToLocalDTO(Local local);
    /**
     * Método para convertir lista de modelos de locales a lista DTO
     * @param local Lista de locales
     * @return Lista DTO de los locales
     */
    List<LocalDTO> localToLocalDTOList(List<Local> local);

    /**
     * Método para convertir modelo del local a DTO con la información reducida
     * @param local Local
     * @return DTO del local con la información reducida
     */
    @Mappings({
            @Mapping(source = "idLocal", target = "idLocal"),
            @Mapping(source = "direccion", target = "direccion")
    })
    LocalPagoDTO localToLocalPagoDTO(Local local);
    /**
     * Método para convertir lista de modelos de locales a lista DTO con la información reducida
     * @param local Lista de locales
     * @return Lista DTO de los locales con la información reducida
     */
    List<LocalPagoDTO> localToLocalPagoDTOList(List<Local> local);

}