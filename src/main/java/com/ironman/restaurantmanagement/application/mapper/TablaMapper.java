package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.presistence.entity.Tabla;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct annotations
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TablaMapper {

    TablaDto   toDto(Tabla tabla);

    TablaSmallDto toSmallDto(Tabla tabla);

    TablaSaveDto toSaveDto(Tabla tabla);

    Tabla entity(TablaBodyDto tablaBodyDto);

    Tabla updateEntity(@MappingTarget Tabla tabla, TablaBodyDto tablaBodyDto);
}