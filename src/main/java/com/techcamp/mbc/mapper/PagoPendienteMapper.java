package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.PagoPendienteDTO;
import com.techcamp.mbc.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los pagos pendientes
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface PagoPendienteMapper {

    /**
     * Método para convertir modelo del pago pendiente a DTO
     * @param pago pago pendiente
     * @return DTO del pago pendiente
     */
    @Mappings({
            @Mapping(source = "idPago", target = "idPago"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"),
            @Mapping(source = "valorPagado", target = "valorPagado"),
            @Mapping(source = "fechaPago", target = "fechaPago"),
            @Mapping(source = "tipoPago.tipo", target = "tipoPago"),
            @Mapping(source = "estadoPago.estado", target = "estadoPago"),
            @Mapping(source = "local.direccion", target = "local")
    })
    PagoPendienteDTO pagoToPagoPendienteDTO(Pago pago);
    /**
     * Método para convertir lista de modelos de pagos pendientes a lista DTO
     * @param pago Lista de pagos pendientes
     * @return Lista DTO de los pagos pendientes
     */
    List<PagoPendienteDTO> pagoToPagoPendienteDTOList(List<Pago> pago);

}