package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.tabla.TablaBodyDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSaveDto;
import com.ironman.restaurantmanagement.application.dto.tabla.TablaSmallDto;
import com.ironman.restaurantmanagement.application.mapper.TablaMapper;
import com.ironman.restaurantmanagement.application.service.TablaService;
import com.ironman.restaurantmanagement.presistence.entity.Tabla;
import com.ironman.restaurantmanagement.presistence.repository.TablaRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

//Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotations
@Service
public class TablaServiceImpl implements TablaService {

    private final TablaRepository tablaRepository;
    private final TablaMapper tablaMapper;

    @Override
    public List<TablaSmallDto> findAll() {
        return tablaRepository.findAll()
                .stream()
                // .filter(tabla -> !(State.DISABLED.getValue()).equals(tabla.getState()))
                .map(tablaMapper::toSmallDto)
                .toList();
    }

    @Override
    public TablaDto findById(Long id) throws DataNotFoundException {
        return tablaRepository.findById(id)
                .map(tablaMapper::toDto)
                .orElseThrow(() -> tablaDataNotFoundException(id));
    }


    @Override
    public TablaSaveDto create(TablaBodyDto tablaBodyDto) {
        Tabla tabla = tablaMapper.entity(tablaBodyDto);
        tabla.setState(State.ENABLED.getValue());
        tabla.setCreatedAt(LocalDateTime.now());

        return tablaMapper.toSaveDto(tablaRepository.save(tabla));
    }

    @Override
    public TablaSaveDto update(Long id, TablaBodyDto tablaBodyDto) throws DataNotFoundException {
        Tabla tabla = tablaRepository.findById(id)
                .orElseThrow(() -> tablaDataNotFoundException(id));

        tablaMapper.updateEntity(tabla, tablaBodyDto);
        tabla.setUpdatedAt(LocalDateTime.now());

        return tablaMapper.toSaveDto(tablaRepository.save(tabla));
    }

    @Override
    public TablaSaveDto disable(Long id) throws DataNotFoundException {
        Tabla tabla = tablaRepository.findById(id)
                .orElseThrow(() -> tablaDataNotFoundException(id));
        tabla.setState(State.DISABLED.getValue());

        return tablaMapper.toSaveDto(tablaRepository.save(tabla));
    }

    @Override
    public List<TablaSmallDto> findByStateIgnoreCaseOrderByIdDesc(String state) {
        return tablaRepository.findByStateIgnoreCaseOrderByIdDesc(state)
                .stream()
                .map(tablaMapper::toSmallDto)
                .toList();

    }

    @Override
    public List<TablaSmallDto> findByName(String name) {
        return tablaRepository.findByName(name)
                .stream()
                .map(tablaMapper::toSmallDto)
                .toList();

    }

    @Override
    public List<TablaSmallDto> findAllByFilters(String name, String state) {
        return tablaRepository.findAllByFilters(name, state)
                .stream()
                .map(tablaMapper::toSmallDto)
                .toList();
    }


//    private static Supplier<DataNotFoundException> tablaDataNotFoundExceptionSupplier(Long id) {
//        return () -> new DataNotFoundException(" Tabla not found width id: " + id);
//    }

    private DataNotFoundException tablaDataNotFoundException(Long id) {
        return new DataNotFoundException(" Tabla not found width id: " + id);
    }
}
