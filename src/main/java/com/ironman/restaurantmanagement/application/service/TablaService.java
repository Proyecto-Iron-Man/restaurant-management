package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;

import java.util.List;

public interface TablaService {

    List<TablaSmallDto> findAll();

    TablaDto findById(Long id) throws DataNotFoundException;

    TablaSaveDto create(TablaBodyDto tablaBodyDto);

    TablaSaveDto update(Long id, TablaBodyDto tablaBodyDto) throws DataNotFoundException;

    TablaSaveDto disable(Long id) throws DataNotFoundException;

    List<TablaSmallDto> findByStateIgnoreCaseOrderByIdDesc(String state);

    List<TablaSmallDto> findByName(String name);

    List<TablaSmallDto> findAllByFilters(String name, String state);

}
