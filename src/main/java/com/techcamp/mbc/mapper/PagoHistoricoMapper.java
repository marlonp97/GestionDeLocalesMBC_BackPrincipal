package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.PagoHistoricoDTO;
import com.techcamp.mbc.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los pagos finalizados
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface PagoHistoricoMapper {

    /**
     * Método para convertir modelo del pago finalizado a DTO
     * @param pago Pago finalizado
     * @return DTO del pago finalizado
     */
    @Mappings({
            @Mapping(source = "idPago", target = "idPago"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"),
            @Mapping(source = "soporte", target = "soporte"),
            @Mapping(source = "valorPagado", target = "valorPagado"),
            @Mapping(source = "fechaPago", target = "fechaPago"),
            @Mapping(source = "tipoPago.tipo", target = "tipoPago"),
            @Mapping(source = "local.direccion", target = "local")
    })
    PagoHistoricoDTO pagoToPagoHistoricoDTO(Pago pago);
    /**
     * Método para convertir lista de modelos de pagos finalizados a lista DTO
     * @param pago Lista de pagos finalizados
     * @return Lista DTO de los pagos finalizados
     */
    List<PagoHistoricoDTO> pagoToPagoHistoricoDTOList(List<Pago> pago);

}