package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;

import java.util.List;

public interface TablaService {

    List<TablaSmallDto> findAll();

    TablaDto findById(Long id);

    TablaSaveDto create(TablaBodyDto tablaBodyDto);

    TablaSaveDto update(Long id, TablaBodyDto tablaBodyDto);

    TablaSaveDto disable(Long id);

    List<TablaSmallDto> findByStateIgnoreCaseOrderByIdDesc(String state);

}
