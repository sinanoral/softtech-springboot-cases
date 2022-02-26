package com.patika.service;

import com.patika.model.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity, D extends JpaRepository<E, Long>> {
    private final D dao;

    public List<E> getAll() {
        return dao.findAll();
    }

    public E getById(Long id) {
        return dao.findById(id).orElseThrow();
    }

    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public void create(E dto) {
        dao.save(dto);
    }

    public void delete(Long id) {
        dao.deleteById(id);
    }
}
