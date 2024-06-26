package com.ironman.restaurantmanagement.shared.state.mapper;

import com.ironman.restaurantmanagement.shared.state.enums.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

// Mapstruct annotations
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {
    @ValueMapping(source = "A", target = "ENABLED")
    @ValueMapping(source = "E", target = "DISABLED")
    State toState(String value);
}
