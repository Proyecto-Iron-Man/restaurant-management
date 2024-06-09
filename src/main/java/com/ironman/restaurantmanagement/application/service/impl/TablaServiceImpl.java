package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.application.service.TablaService;

import java.util.List;

public class TablaServiceImpl implements TablaService {
    @Override
    public List<TablaSmallDto> findAll() {
        return List.of();
    }

    @Override
    public TablaDto findById(Long id) {
        return null;
    }

    @Override
    public TablaSaveDto create(TablaBodyDto tablaBodyDto) {
        return null;
    }

    @Override
    public TablaSaveDto update(Long id, TablaBodyDto tablaBodyDto) {
        return null;
    }

    @Override
    public TablaSaveDto disable(Long id) {
        return null;
    }
}
