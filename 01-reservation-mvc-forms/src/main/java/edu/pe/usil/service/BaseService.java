package edu.pe.usil.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<Table, k> {
    List<Table> findAll();
    void save(Table t);
    void update(Table t);
    void delete(Table t);
}
