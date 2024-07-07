package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceBodyDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceSavedDto;
import com.ironman.restaurantmanagement.application.dto.invoicedetail.InvoiceDetailBodyDto;
import com.ironman.restaurantmanagement.application.dto.invoicedetail.InvoiceDetailDto;
import com.ironman.restaurantmanagement.presistence.entity.Invoice;
import com.ironman.restaurantmanagement.presistence.entity.InvoiceDetail;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct annotations
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CustomerMapper.class, ProductMapper.class, StateMapper.class, UserMapper.class}
)
public interface InvoiceMapper {
    InvoiceDto toDto(Invoice invoice);

    InvoiceSavedDto toSavedDto(Invoice invoice);

    Invoice toEntity(InvoiceBodyDto invoiceBody);

    void updateEntity(@MappingTarget Invoice invoice, InvoiceBodyDto invoiceBody);


    InvoiceDetailDto toDetailDto(InvoiceDetail invoiceDetail);

    @Mapping(target = "id.productId", source = "productId")
    InvoiceDetail toDetailEntity(InvoiceDetailBodyDto invoiceDetailBody);

    @Mapping(target = "id.productId", source = "productId")
    void updateDetailEntity(@MappingTarget InvoiceDetail invoiceDetail, InvoiceDetailBodyDto invoiceDetailBody);
}
