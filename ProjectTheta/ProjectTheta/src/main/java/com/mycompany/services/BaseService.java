package com.mycompany.services;

import java.util.List;

public interface BaseService<Table> {
    List<Table> findAll();
    void save(Table t);
    void update(Table t);
    void delete(Table t);
}
